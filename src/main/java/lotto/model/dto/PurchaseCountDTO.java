package lotto.model.dto;

import lotto.model.PurchaseCount;

public class PurchaseCountDTO {
    private final int autoCount;
    private final int manualCount;

    private PurchaseCountDTO(int autoCount, int manualCount) {
        this.autoCount = autoCount;
        this.manualCount = manualCount;
    }

    public int getAutoCount() {
        return autoCount;
    }

    public int getManualCount() {
        return manualCount;
    }

    public static PurchaseCountDTO of(PurchaseCount purchaseCount) {
        return new PurchaseCountDTO(purchaseCount.getAuto(), purchaseCount.getManual());
    }
}
