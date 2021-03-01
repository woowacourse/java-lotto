package lotto.domain;

public class PurchaseCount {

    private final int totalCount;
    private final int manualPurchaseCount;
    private final int autoPurchaseCount;

    public PurchaseCount(int totalCount, int manualPurchaseCount) {
        validate(totalCount, manualPurchaseCount);
        this.totalCount = totalCount;
        this.manualPurchaseCount = manualPurchaseCount;
        this.autoPurchaseCount = totalCount - manualPurchaseCount;
    }

    private void validate(int totalCount, int manualPurchaseCount) {
        if (totalCount < 0) {
            throw new IllegalArgumentException("0이상의 숫자를 입력하세요. ");
        }
        if (manualPurchaseCount > totalCount) {
            throw new IllegalArgumentException("총 구매갯수보다 더많은 수동 로또를 구매할 수 없습니다.");
        }
    }

    public int getManualPurchaseCount() {
        return manualPurchaseCount;
    }

    public int getAutoPurchaseCount() {
        return autoPurchaseCount;
    }
}
