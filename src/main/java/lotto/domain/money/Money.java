package lotto.domain.money;

import lotto.domain.lotto.Lotto;

import java.util.Objects;

public class Money {
    private static final int ZERO = 0;
    private static final int HUNDRED = 100;
    private final int money;

    private Money(int money) {
        this.money = money;
    }

    public static Money create(int money) {
        if (money < Lotto.PRICE || money % Lotto.PRICE != ZERO) {
            throw new IllegalMoneyException();
        }
        return new Money(money);
    }


    public double calculatePercentage(double sum) {
        return sum / money * HUNDRED;
    }

    public int getLottoCount() {
        return this.money / Lotto.PRICE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Money money1 = (Money) o;
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
