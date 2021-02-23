package lotto.domain;

public class PurchaseCount {

    private static final String PURCHASE_COUNT_ERROR = "[Error] 구매 개수는 음수가 될 수 없습니다.";
    private final int value;

    public PurchaseCount(int value) {
        if (value < 0) {
            throw new IllegalArgumentException(PURCHASE_COUNT_ERROR);
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
