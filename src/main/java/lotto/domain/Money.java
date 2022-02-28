package lotto.domain;

import java.util.Objects;

public class Money {

    private static final String BUY_ERROR_MESSAGE = "가격 부족으로 구매가 불가능 합니다.";

    private final int amount;

    public Money(int price) {
        this.amount = price;
    }

    public Money calculateProduct(int price, int lottoCount) {
        validateAmount(price, lottoCount);
        return new Money(amount - lottoCount * price);
    }

    private void validateAmount(int price, int lottoCount) {
        if (amount < price * lottoCount) {
            throw new IllegalArgumentException(BUY_ERROR_MESSAGE);
        }
    }

    public int getProductCount(int price) {
        return amount / price;
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
