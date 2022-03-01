package lotto.domain;

public class LottoGame {

    private final PurchaseAmount purchaseAmount;
    private final int manualLottoCount;

    public LottoGame(PurchaseAmount purchaseAmount, int manualLottoCount) {
        this.purchaseAmount = purchaseAmount;

        validateCanPurchase(manualLottoCount);
        this.manualLottoCount = manualLottoCount;
    }

    private void validateCanPurchase(int count) {
        if (!purchaseAmount.canPurchase(count)) {
            throw new IllegalArgumentException("구입 금액으로 살 수 있는 수량이어야 합니다.");
        }
    }

    public int getManualLottoCount() {
        return manualLottoCount;
    }
}
