package lotterymachine.domain.vo;

import lotterymachine.domain.LotteryTicket;

import java.util.Objects;

public class Money {
    private static final String TERMS_OF_PURCHASE = "로또 구매는 기본 1000원 이상부터 할 수 있습니다.";

    private final int value;

    public Money(int value) {
        validateValue(value);
        this.value = value;
    }

    private void validateValue(int value) {
        if (value < LotteryTicket.PER_PRICE) {
            throw new IllegalArgumentException(TERMS_OF_PURCHASE);
        }
    }

    public int getValue() {
        return value;
    }

    public int getPurchasePossibleCount() {
        return this.value / LotteryTicket.PER_PRICE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
