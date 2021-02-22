package lotto.domain;

import java.util.Objects;

public class Money {
    private static final String AMOUNT_MUST_NOT_NEGATIVE_ERROR_MSG_FORMAT = "금액은 0보다 작을 수 없습니다. 입력된 금액 : %d";
    private final int money;

    public Money(int money) {
        validateNegative(money);
        this.money = money;
    }

    public Money(Money money) {
        this(money.getMoney());
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

    public int divide(Money that) {
        return this.money / that.money;
    }

    public Money minus(Money that) {
        return new Money(this.money - that.money);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;
        Money that = (Money) o;
        return getMoney() == that.getMoney();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMoney());
    }
}
