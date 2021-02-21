package lotto.domain;

import java.util.Objects;

public class Money {
    public static final int LOTTO_PRICE = 1000;
    public static final String NOT_ENOUGH_MONEY_ERROR = String.format("%d원 이상의 금액만 입력 가능합니다.", LOTTO_PRICE);

    private final int money;

    public Money(int money) {
        validateNotEnoughMoney(money);
        this.money = money;
    }

    private void validateNotEnoughMoney(int money) {
        if (money < LOTTO_PRICE) {
            throw new IllegalArgumentException(NOT_ENOUGH_MONEY_ERROR);
        }
    }

    public Money calculateMoneyActuallyInvested() {
        return new Money(calculateAffordableNumberOfLotto() * LOTTO_PRICE);
    }

    public int calculateAffordableNumberOfLotto() {
        return money / LOTTO_PRICE;
    }

    public float calculateProfitRate(Money profit) {
        return (float) profit.money / this.money;
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
