package lotto.domain.money;

import lotto.domain.lotto.Lotto;

import java.util.Objects;

public class Money {
    private final int money;

    public Money(String input) {
        int currentMoney = Integer.parseInt(input);
        if (currentMoney < Lotto.PRICE || currentMoney % Lotto.PRICE != 0) {
            throw new IllegalMoneyException();
        }
        this.money = Integer.parseInt(input);
    }


    public double calculatePercentage(double sum) {
        return sum / money;
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
