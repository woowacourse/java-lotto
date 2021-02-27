package domain;

import java.util.List;

public class WinningResult {

    private final Money money;
    private final List<Ranking> rankings;

    public WinningResult(WinningNumbers winningNumbers, LottoTickets lottoTickets,
                         Money money) {
        this.rankings = winningNumbers.calculateRankings(lottoTickets);
        this.money = money;
    }

    public int countNumberOfRank(final Ranking ranking) {
        return (int) rankings.stream()
            .filter(current -> current == ranking)
            .count();
    }

    public double calculateProfitRate() {
        return ((double) sumTotalProfit()) / money.getValue();
    }

    private int sumTotalProfit() {
        return rankings.stream()
            .mapToInt(Ranking::getMoney)
            .sum();
    }
}