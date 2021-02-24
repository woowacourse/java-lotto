package lotto.domain;

public class AutoPurchaseCount {
    private final int autoPurchaseCount;

    public AutoPurchaseCount(int autoPurchaseCount) {
        validate(autoPurchaseCount);
        this.autoPurchaseCount = autoPurchaseCount;
    }

    private void validate(int autoPurchaseCount) {
        if (autoPurchaseCount < 0) {
            throw new IllegalArgumentException("구매금액을 초과하였습니다. 갯수를 확인해주세요. ");
        }
    }


    public int getAutoPurchaseCount() {
        return autoPurchaseCount;
    }
}

