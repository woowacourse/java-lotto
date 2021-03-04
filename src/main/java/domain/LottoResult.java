package domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LottoResult.java
 * 당첨 결과(구입한 모든 로또 티켓에 대한 등수 통계) 클래스
 *
 * @author Kimun Kim, github.com/tributetothemoon
 * @author Daeun Lee, github.com/da-nyee
 */
public class LottoResult {
    private static final Integer INITIAL_VALUE_ONE = 1;

    private final Map<Rank, Integer> resultStatisticsMap;

    private LottoResult(Map<Rank, Integer> resultStatisticsMap) {
        this.resultStatisticsMap = Collections.unmodifiableMap(resultStatisticsMap);
    }

    public static LottoResult of(WinningNumbers winningNumbers, LottoTickets lottoTickets) {
        Map<Rank, Integer> resultStatisticsMap = new HashMap<>();
        for (LottoTicket lottoTicket : lottoTickets.lottoTickets()) {
            Rank rank = winningNumbers.calculateRank(lottoTicket);
            addCount(resultStatisticsMap, rank);
        }
        return new LottoResult(resultStatisticsMap);
    }

    private static void addCount(Map<Rank, Integer> resultStatisticsMap, Rank rank) {
        if (resultStatisticsMap.containsKey(rank)) {
            int count = resultStatisticsMap.get(rank);
            resultStatisticsMap.put(rank, count + 1);
            return;
        }
        resultStatisticsMap.put(rank, INITIAL_VALUE_ONE);
    }

    public Map<Rank, Integer> result() {
        return this.resultStatisticsMap;
    }

    public Money getReward() {
        long total = 0;
        for (Rank rank : resultStatisticsMap.keySet()) {
            long count = resultStatisticsMap.get(rank);
            total += count * rank.getReward().toLong();
        }
        return Money.from(total);
    }
}
