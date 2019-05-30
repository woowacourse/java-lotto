package lotto.domain;

import lotto.exception.IllegalNumberBoundException;

import java.util.Objects;

public class Money {
    // TODO 이름 바꿔...
    private static final int DIVIDING_STANDARD = 1000;

    private final int money;

    public Money(final int money) {
        this.money = money;

        validateMinimumMoneyInput(money);
    }

    public int getTicketCount() {
        return money / DIVIDING_STANDARD;
    }

    private void validateMinimumMoneyInput(int money) {
        if (money < DIVIDING_STANDARD) {
            throw new IllegalNumberBoundException("최소 로또 구매 금액은 1000원입니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money1 = (Money) o;
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
