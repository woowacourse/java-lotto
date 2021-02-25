package domain;

import java.util.List;

public class WinningResult {

    private final List<Ranking> rankings;

    public WinningResult(WinningNumbers winningNumbers, List<LottoTicket> lottoTickets) {
        this.rankings = winningNumbers.getRankings(lottoTickets);
    }

    public int countNumberOfRank(final Ranking ranking) {
        return (int) rankings.stream()
                .filter(current -> current == ranking)
                .count();
    }

    public double getProfitRate() {
        return ((double) getTotalProfit()) / 10000;
    }

    private int getTotalProfit() {
        return rankings.stream()
                .mapToInt(Ranking::getMoney)
                .sum();
    }
}