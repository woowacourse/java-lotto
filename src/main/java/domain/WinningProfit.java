package domain;

import java.util.Map;

public class WinningProfit {

    private final Map<LottoRank, Integer> winningResult;

    public WinningProfit(final Map<LottoRank, Integer> winningResult) {
        this.winningResult = winningResult;
    }

    public int calculateTotalPrice() {
        return winningResult.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public double calculateProfitRate(final int amount) {
        return calculateTotalPrice() / (double) amount;
    }

}
