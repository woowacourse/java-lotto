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
    private static final Integer ZERO = 0;

    private final Map<Rank, Integer> resultStatisticsMap;

    private LottoResult(Map<Rank, Integer> resultStatisticsMap) {
        this.resultStatisticsMap = Collections.unmodifiableMap(resultStatisticsMap);
    }

    public static LottoResult of(WinningNumbers winningNumbers, List<LottoTicket> lottoTickets) {
        Map<Rank, Integer> resultStatisticsMap = new HashMap<>();
        for (Rank rank : Rank.values()) {
            resultStatisticsMap.put(rank, ZERO);
        }
        for (LottoTicket lottoTicket : lottoTickets) {
            Rank rank = winningNumbers.calculateRank(lottoTicket);
            Integer count = resultStatisticsMap.get(rank);
            resultStatisticsMap.put(rank, count + 1);
        }
        return new LottoResult(resultStatisticsMap);
    }

    public Map<Rank, Integer> result() {
        return this.resultStatisticsMap;
    }

    public Money getReward() {
        long total = 0;
        for (Rank rank : Rank.values()) {
            long count = resultStatisticsMap.get(rank);
            total += count * rank.getReward().toLong();
        }
        return new Money(total);
    }
}
