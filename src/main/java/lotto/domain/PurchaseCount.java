package lotto.domain;

public class PurchaseCount {

    public static final String PURCHASE_COUNT_ERROR = "[Error] 최소 구매 개수는 1개 이상 입니다.";

    private final int value;

    public PurchaseCount(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException(PURCHASE_COUNT_ERROR);
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
