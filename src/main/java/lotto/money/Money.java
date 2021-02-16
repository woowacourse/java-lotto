package lotto.money;

import java.util.Objects;

public class Money {
    private static final int MINIMUM_PRICE = 1000;

    private int money;

    public Money(String money) {
        this.money = validate(money);
    }

    private int validate(String money) {
        int value = validateNumber(money);
        checkMinimum(value);
        return value;
    }

    private void checkMinimum(int value) {
        if (value < MINIMUM_PRICE) {
            throw new IllegalArgumentException("[error] 1000원 이상의 금액이 필요합니다.");
        }
    }

    private int validateNumber(String money) {
        return Integer.parseInt(money);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money1 = (Money) o;
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
