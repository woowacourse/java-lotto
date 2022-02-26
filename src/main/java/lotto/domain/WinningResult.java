package lotto.domain;

import lotto.domain.matchkind.LottoMatchKind;

import java.util.Map;
import java.util.Objects;

public class WinningResult {
    private final Map<LottoMatchKind, Integer> winningNumberByMatchKind;
    private final double profitRate;

    public WinningResult(final Map<LottoMatchKind, Integer> winningNumberByMatchKind, double profitRate) {
        this.winningNumberByMatchKind = winningNumberByMatchKind;
        this.profitRate = profitRate;
    }

    public Map<LottoMatchKind, Integer> getWinningNumberByKind() {
        return winningNumberByMatchKind;
    }

    public double getProfitRate() {
        return profitRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningResult that = (WinningResult) o;
        return Double.compare(that.profitRate, profitRate) == 0 && Objects.equals(winningNumberByMatchKind, that.winningNumberByMatchKind);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningNumberByMatchKind, profitRate);
    }
}
