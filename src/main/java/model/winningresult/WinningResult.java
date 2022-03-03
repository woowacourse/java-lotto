package model.winningresult;

import java.util.Map;

import model.money.TotalPurchaseMoney;
import model.rank.Rank;

public class WinningResult {
    private static final int LOTTO_PRICE = 1000;

    private final Map<Rank, Integer> winningResult;

    public WinningResult(final Map<Rank, Integer> winningResult) {
        this.winningResult = winningResult;
    }

    public Map<Rank, Integer> getWinningResult() {
        return winningResult;
    }

    public Double getRateOfReturn(final TotalPurchaseMoney totalPurchaseMoney) {
        int totalInsertMoney = totalPurchaseMoney.getTotalPurchaseLottoCount() * LOTTO_PRICE;
        int totalReturn = calculateTotalReturn();
        return totalReturn / (double) totalInsertMoney;
    }

    private int calculateTotalReturn() {
        return winningResult.entrySet().stream()
                .mapToInt(rankResult -> rankResult.getKey().getPrize() * rankResult.getValue())
                .sum();
    }
}
