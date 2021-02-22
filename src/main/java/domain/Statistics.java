package domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Statistics.java
 * 당첨 통계(구입한 모든 로또 티켓에 대한 등수 통계) 클래스
 *
 * @author Kimun Kim, github.com/tributetothemoon
 * @author Daeun Lee, github.com/da-nyee
 */
public class Statistics {
    private static final Integer ZERO = 0;

    private final Map<Rank, Integer> lottoStatistics = new HashMap<>();

    public Statistics(WinningNumbers winningNumbers, LottoTickets lottoTickets) {
        for (Rank rank : Rank.values()) {
            lottoStatistics.put(rank, ZERO);
        }
        calculate(winningNumbers, lottoTickets);
    }

    private void calculate(WinningNumbers winningNumbers, LottoTickets lottoTickets) {
        for (LottoTicket lottoTicket : lottoTickets.lottoTickets()) {
            Rank rank = winningNumbers.calculateRank(lottoTicket);
            Integer count = lottoStatistics.get(rank);
            lottoStatistics.put(rank, count + 1);
        }
    }

    public Map<Rank, Integer> result() {
        return Collections.unmodifiableMap(lottoStatistics);
    }

    public Money getReward() {
        long total = 0;
        for (Rank rank : Rank.values()) {
            long count = lottoStatistics.get(rank);
            total += count * rank.getReward().toLong();
        }
        return new Money(Long.toString(total));
    }
}
