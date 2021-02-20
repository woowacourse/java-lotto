package lotto.domain;

import java.util.Objects;

public class Money {
    private static final int LOTTO_PRICE = 1000;

    private final int money;

    public Money(String money) {
        validateNumber(money);
        validateNotEnoughMoney(money);
        this.money = Integer.parseInt(money);
    }

    private void validateNumber(String money) {
        try {
            Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("금액은 정수만 입력 가능합니다");
        }
    }

    private void validateNotEnoughMoney(String money) {
        if (Integer.parseInt(money) < LOTTO_PRICE) {
            throw new IllegalArgumentException("1000원 이상의 금액만 입력 가능합니다.");
        }
    }

    public int calculateAffordableNumberOfLotto() {
        return money / LOTTO_PRICE;
    }

    public float calculateProfitRate(int profit) {
        return (float) profit / money;
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
