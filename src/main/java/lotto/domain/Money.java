package lotto.domain;

import java.util.Objects;

public class Money {
    private static final int MIN_MONEY = 1_000;
    private static final int MAX_MONEY = 100_000;
    private static final int MONEY_UNIT = 1_000;
    private int money;

    public Money(int money) {
        checkValid(money);
        this.money = money;
    }

    private void checkValid(int money) {
        if (money < MIN_MONEY || money > MAX_MONEY) {
            throw new IllegalArgumentException(String.format("금액의 범위는 %d ~ %d 입니다.", MIN_MONEY, MAX_MONEY));
        }
        if (money % MONEY_UNIT != 0) {
            throw new IllegalArgumentException(String.format("금액의 단위는 %d 입니다.", MONEY_UNIT));
        }
    }

    public int getMoney() {
        return money;
    }

    public int calCountOfLotto() {
        return money / Lotto.LOTTO_PRICE;
    }

    double calculateYield(long revenue) {
        return revenue / (double) money * 100;
    }

    boolean canBuyLotto(int count) {
        return count <= calCountOfLotto();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;
        Money money1 = (Money) o;
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
