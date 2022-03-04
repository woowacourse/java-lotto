package domain;

public class SelfPurchaseCount {
    public static final String MIN_COUNT_ERROR_MESSAGE = "0이상의 값을 횟수로 입력해야 합니다.";
    private final int count;

    public SelfPurchaseCount(int count) {
        validateCount(count);
        this.count = count;
    }

    public void validateCount(int count) {
        if (count < 0) {
            throw new IllegalArgumentException(MIN_COUNT_ERROR_MESSAGE);
        }
    }

    public int getValue() {
        return count;
    }
}
