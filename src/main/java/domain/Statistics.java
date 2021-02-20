package domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Statistics {
    private static final Integer INITIAL_COUNT = 0;

    private final Map<Rank, Integer> lottoStatistics = new HashMap<>();

    public Statistics(WinningNumber winningNumber, LottoTickets lottoTickets) {
        for (Rank rank : Rank.values()) {
            lottoStatistics.put(rank, INITIAL_COUNT);
        }
        calculate(winningNumber, lottoTickets);
    }

    private void calculate(WinningNumber winningNumber, LottoTickets lottoTickets) {
        for (LottoTicket lottoTicket : lottoTickets.lottoTickets()) {
            Rank rank = winningNumber.calculateRank(lottoTicket);
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
