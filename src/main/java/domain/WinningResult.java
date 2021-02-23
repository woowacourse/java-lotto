package domain;

import java.util.List;

public class WinningResult {

    private final Price price;
    private final List<Ranking> rankings;

    public WinningResult(WinningNumbers winningNumbers, List<LottoTicket> lottoTickets,
                         Price price) {
        this.rankings = winningNumbers.calculateRankings(lottoTickets);
        this.price = price;
    }

    public int countNumberOfRank(final Ranking ranking) {
        return (int) rankings.stream()
            .filter(current -> current == ranking)
            .count();
    }

    public double calculateProfitRate() {
        return ((double) sumTotalProfit()) / price.getValue();
    }

    private int sumTotalProfit() {
        return rankings.stream()
            .mapToInt(Ranking::getMoney)
            .sum();
    }
}