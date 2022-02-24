package domain;

public class Money {

    private static final String ERROR_MESSAGE_MONEY_RANGE = "금액은 0이하일 수 없습니다.";
    private static final int MINIMUM_AMOUNT = 0;

    private final int amount;

    public Money(int amount) {
        validateRange(amount);
        this.amount = amount;
    }

    private void validateRange(int amount) {
        if (amount <= MINIMUM_AMOUNT) {
            throw new IllegalArgumentException(ERROR_MESSAGE_MONEY_RANGE);
        }
    }

    public int getAmount() {
        return amount;
    }
}
