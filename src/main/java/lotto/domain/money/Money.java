package lotto.domain.money;

import lotto.domain.lotto.Lotto;
import lotto.domain.money.exception.IllegalMoneyException;

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
            throw new IllegalMoneyException("금액은 1000원 이상, 1000원 단위의 금액이여야 합니다.");
        }
        return new Money(money);
    }


    public double calculatePercentage(double sum) {
        return sum / (double) money * (double) HUNDRED;
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
