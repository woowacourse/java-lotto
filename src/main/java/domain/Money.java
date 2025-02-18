package domain;

public class Money {

    private final int amount;

    private Money(int amount) {
        validate(amount);
        this.amount = amount;
    }

    public static Money from(int amount) {
        return new Money(amount);
    }

    public int getAmount() {
        return amount;
    }

    private void validate(int amount) {
        validateNegativeAmount(amount);
    }

    private void validateNegativeAmount(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("돈의 값은 음수가 될 수 없습니다. 입력된 값: " + amount);
        }
    }
}
