package lotto.domain;

import java.util.Map;
import java.util.Objects;

public class WinningResult {

    private final Map<LottoAward, Integer> winningResult;

    public WinningResult(Map<LottoAward, Integer> winningResult) {
        this.winningResult = winningResult;
    }

    public Map<LottoAward, Integer> getWinningResult() {
        return this.winningResult;
    }

    public double calculateProfitRate(final LottoPrice lottoPrice) {
        return lottoPrice.divideFrom(calculateTotalAmount());
    }

    private int calculateTotalAmount() {
        return winningResult.entrySet().stream()
                .mapToInt((entry) -> entry.getKey().getAmount() * entry.getValue())
                .sum();
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof WinningResult that)) {
            return false;
        }
        return Objects.equals(winningResult, that.winningResult);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(winningResult);
    }
}
