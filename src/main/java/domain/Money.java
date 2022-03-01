package domain;

public class Money {
    public static final String NOT_POSITIVE_ERROR_MESSAGE = "금액은 양수로 입력해야 합니다.";

    private final int amount;

    public Money(int amount) {
        validateMoney(amount);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    private void validateMoney(int amount) {
        if (isNotPositiveNumber(amount)) {
            throw new IllegalArgumentException(NOT_POSITIVE_ERROR_MESSAGE);
        }
    }

    private boolean isNotPositiveNumber(int purchaseMoney) {
        return purchaseMoney <= 0;
    }
}
