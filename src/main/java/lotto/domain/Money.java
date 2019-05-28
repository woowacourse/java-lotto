package lotto.domain;

import java.util.Objects;

public class Money {
    private static final int LOTTO_PRICE = 1000;

    private final int price;

    public Money(int price) {
        checkMinimumAmount(price);
        checkChange(price);
        this.price = price;
    }

    private void checkMinimumAmount(int price) {
        if (price < LOTTO_PRICE) {
            throw new IllegalArgumentException("최소 금액보다 적은 금액입니다.");
        }
    }

    private void checkChange(int price) {
        if (price % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("거스름돈이 존재합니다.");
        }
    }

    public int purchaseCount() {
        return price / LOTTO_PRICE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return price == money.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }
}
