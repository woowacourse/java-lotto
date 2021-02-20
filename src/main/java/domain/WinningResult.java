package domain;

import java.util.List;

public class WinningResult {
    private final Price price;
    private final List<Ranking> rankings;

    public WinningResult(final WinningNumbers winningNumbers, final List<LottoTicket> lottoTickets,
                         Price price) {
        this.rankings = winningNumbers.getRankings(lottoTickets);
        this.price = price;
    }

    public int countNumberOfRank(final Ranking ranking) {
        return (int) rankings.stream()
                .filter(current -> current == ranking)
                .count();
    }

    public double getProfitRate() {
        return ((double) getTotalProfit()) / price.getValue();
    }

    private int getTotalProfit() {
        return rankings.stream()
                .mapToInt(Ranking::getMoney)
                .sum();
    }
}