package lotto.domain;

public class ManualPurchaseCount {

    int manualPurchaseCount;

    public ManualPurchaseCount(int manualPurchaseCount){
        validate(manualPurchaseCount);
        this.manualPurchaseCount = manualPurchaseCount;
    }

    private void validate(int manualPurchaseCount) {
        if(manualPurchaseCount < 0){
            throw new IllegalArgumentException("0이상의 숫자를 입력하세요. ");
        }
    }

    public int getManualPurchaseCount() {
        return manualPurchaseCount;
    }
}
