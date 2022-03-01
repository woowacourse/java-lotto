package lotto.domain.winningresult;

import lotto.domain.matchkind.LottoMatchKind;
import lotto.domain.purchaseamount.PurchaseAmount;

import java.util.Map;
import java.util.Objects;

public class WinningResult {
    private final Map<LottoMatchKind, Integer> winningNumberByMatchKind;
    private final PurchaseAmount purchaseAmount;

    public WinningResult(
            final Map<LottoMatchKind, Integer> winningNumberByMatchKind, final PurchaseAmount purchaseAmount) {
        this.winningNumberByMatchKind = winningNumberByMatchKind;
        this.purchaseAmount = purchaseAmount;
    }

    public Map<LottoMatchKind, Integer> getWinningNumberByKind() {
        return winningNumberByMatchKind;
    }

    public double getProfitRate() {
        final long totalProfit = winningNumberByMatchKind.keySet()
                .stream()
                .mapToLong(lottoMatchKind -> lottoMatchKind.getProfit(winningNumberByMatchKind.get(lottoMatchKind)))
                .sum();
        return purchaseAmount.getProfitRate(totalProfit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningResult that = (WinningResult) o;
        return Objects.equals(winningNumberByMatchKind, that.winningNumberByMatchKind) && Objects.equals(purchaseAmount, that.purchaseAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningNumberByMatchKind, purchaseAmount);
    }
}
