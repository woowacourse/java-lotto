package model.winning;

import java.util.Map;

import model.Money;

public class WinningResult {
    private final Map<Rank, Integer> winningResult;

    public WinningResult(final Map<Rank, Integer> winningResult) {
        this.winningResult = winningResult;
    }

    public Map<Rank, Integer> getWinningResult() {
        return winningResult;
    }

    public Double getRateOfReturn(final Money money) {
        int totalInsertMoney = money.getMoney();
        double totalReturn = calculateTotalReturn();
        return totalReturn / (double) totalInsertMoney;
    }

    private int calculateTotalReturn() {
        return winningResult.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();
    }
}
