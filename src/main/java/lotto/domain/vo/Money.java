package lotto.domain.vo;

public class Money {

    private static final String ERROR_NEGATIVE_INPUT_MESSAGE = "금액은 음수를 입력하면 안됩니다.";

    private int money;

    public Money(int money) {
        validateNegative(money);
        this.money = money;
    }

    private void validateNegative(int number) {
        if (number < 0) {
            throw new IllegalArgumentException(ERROR_NEGATIVE_INPUT_MESSAGE);
        }
    }

    public int canBuyNumber(Money amount) {
        return money / amount.money;
    }

    public int get() {
        return money;
    }
}
