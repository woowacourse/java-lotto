package model.winningresult;

import java.util.Map;

import model.money.Money;
import model.rank.Rank;

public class WinningResult {
    private final Map<Rank, Integer> winningResult;

    public WinningResult(final Map<Rank, Integer> winningResult) {
        this.winningResult = winningResult;
    }

    public Map<Rank, Integer> getWinningResult() {
        return winningResult;
    }

    public Double getRateOfReturn(final Money money) {
        int totalInsertMoney = money.getPurchaseCount();
        int totalReturn = calculateTotalReturn();
        return totalReturn / (double) totalInsertMoney;
    }

    private int calculateTotalReturn() {
        return winningResult.entrySet().stream()
                .mapToInt(rankResult -> rankResult.getKey().getPrize() * rankResult.getValue())
                .sum();
    }
}
