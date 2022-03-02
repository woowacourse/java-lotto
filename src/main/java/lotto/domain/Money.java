package lotto.domain;

import java.util.Objects;

public class Money {

    private static final String BUY_ERROR_MESSAGE = "가격 부족으로 구매가 불가능 합니다.";

    private final int amount;

    public Money(int amount) {
        this.amount = amount;
    }

    public Money calculateProduct(Money money, int count) {
        int totalPrice = money.amount * count;
        validateAmount(totalPrice);
        return new Money(amount - totalPrice);
    }

    public int getProductCount(Money money) {
        validateAmount(money.amount);
        return amount / money.amount;
    }

    private void validateAmount(int price) {
        if (amount < price) {
            throw new IllegalArgumentException(BUY_ERROR_MESSAGE);
        }
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
