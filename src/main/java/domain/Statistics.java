package domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Statistics {
    private static final Integer ZERO = 0;

    private final Map<Rank, Integer> lottoStatistics = new HashMap<>();

    public Statistics(WinningNumber winningNumber, LottoTickets lottoTickets) {
        for (Rank rank : Rank.values()) {
            lottoStatistics.put(rank, ZERO);
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
}
