package lotto.domain;

public class Money {
    public static final String AMOUNT_MUST_NOT_NEGATIVE_ERROR_MSG_FORMAT = "금액은 0보다 작을 수 없습니다. 입력된 금액 : %d";
    private final int money;

    public Money(int money) {
        validateNegative(money);
        this.money = money;
    }

    private void validateNegative(int money) {
        if (money < 0) {
            throw new IllegalArgumentException(String.format(AMOUNT_MUST_NOT_NEGATIVE_ERROR_MSG_FORMAT, money));
        }
    }

    public int getMoney() {
        return money;
    }

    public Money multiply(int times) {
        return new Money(money * times);
    }

    public Money minus(Money that) {
        return new Money(this.money - that.money);
    }
}
