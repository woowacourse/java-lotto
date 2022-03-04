package lotto.domain.winningresult;

import lotto.domain.matchkind.WinningKind;
import lotto.domain.purchaseamount.TotalPurchaseAmount;

import java.util.Map;
import java.util.Objects;

public class WinningResult {
    private final Map<WinningKind, Integer> winningNumberByMatchKind;
    private final TotalPurchaseAmount totalPurchaseAmount;

    public WinningResult(
            final Map<WinningKind, Integer> winningNumberByMatchKind, final TotalPurchaseAmount totalPurchaseAmount) {
        this.winningNumberByMatchKind = winningNumberByMatchKind;
        this.totalPurchaseAmount = totalPurchaseAmount;
    }

    public Map<WinningKind, Integer> getWinningNumberByKind() {
        return winningNumberByMatchKind;
    }

    public double getProfitRate() {
        final long totalProfit = winningNumberByMatchKind.keySet()
                .stream()
                .mapToLong(lottoMatchKind -> lottoMatchKind.getProfit(winningNumberByMatchKind.get(lottoMatchKind)))
                .sum();
        return totalPurchaseAmount.getProfitRate(totalProfit);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningResult that = (WinningResult) o;
        return Objects.equals(winningNumberByMatchKind, that.winningNumberByMatchKind) && Objects.equals(totalPurchaseAmount, that.totalPurchaseAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningNumberByMatchKind, totalPurchaseAmount);
    }
}
