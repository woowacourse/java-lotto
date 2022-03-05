package model.winningresult;

import java.util.Map;

import model.rank.Rank;

public class WinningResult {
    private static final int LOTTO_PRICE = 1000;

    private final Map<Rank, Integer> value;

    public WinningResult(final Map<Rank, Integer> value) {
        this.value = value;
    }

    public Map<Rank, Integer> getValue() {
        return value;
    }

    public Double sendRateOfReturn(final int totalLottoCount) {
        int totalInsertMoney = totalLottoCount * LOTTO_PRICE;
        int totalReturn = calculateTotalReturn();
        return totalReturn / (double) totalInsertMoney;
    }

    private int calculateTotalReturn() {
        return value.entrySet().stream()
                .mapToInt(rankResult -> rankResult.getKey().getPrize() * rankResult.getValue())
                .sum();
    }
}
