package lotto.domain.vo;

public class ManualPurchaseCount {

    private static final String ERROR_NEGATIVE_INPUT_MESSAGE = "수동 로또 구매 횟수는 음수로 입력하면 안됩니다.";

    private final int count;

    public ManualPurchaseCount(int count) {
        validateNegative(count);
        this.count = count;
    }

    private void validateNegative(int number) {
        if (number < 0) {
            throw new IllegalArgumentException(ERROR_NEGATIVE_INPUT_MESSAGE);
        }
    }

    public int get() {
        return count;
    }

    public boolean canBuy() {
        return count > 0;
    }
}
