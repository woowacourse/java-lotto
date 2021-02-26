package lotto.domain;

import java.util.Objects;

public class Money {
    private static final int ZERO = 0;
    private final int money;

    public Money(int money) {
        validateUnderZero(money);
        this.money = money;
    }

    private void validateUnderZero(int money) {
        if (money < ZERO) {
            throw new IllegalArgumentException(
                String.format("돈은 0보다 작을 수 없습니다. 입력된 금액 : %d", money));
        }
    }

    public int getMoney() {
        return money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Money)) {
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
