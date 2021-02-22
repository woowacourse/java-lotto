package lotto.domain;

import java.util.Objects;

public class Money {
    public static final int LOTTO_PRICE = 1000;
    public static final String MANUAL_LOTTO_QUANTITY_ERROR = "수동 로또를 살 돈이 모자랍니다.";

    private final long money;

    public Money(long money) {
        this.money = money;
    }

    public Money(int money) {
        this.money = money;
    }

    public Money getMoneyActuallyInvested() {
        return new Money(getAffordableLottoQuantity() * LOTTO_PRICE);
    }

    public int getAffordableLottoQuantity() {
        return (int) money / LOTTO_PRICE;
    }

    public float getProfitRate(Money profit) {
        return (float) profit.money / this.money;
    }

    public void validateAffordability(int lottoQuantity) {
        if (!isAffordable(lottoQuantity)) {
            throw new IllegalArgumentException(MANUAL_LOTTO_QUANTITY_ERROR);
        }
    }

    private boolean isAffordable(int lottoQuantity) {
        return 0 <= calculateChange(lottoQuantity);
    }

    public Money getChange(int lottoQuantity) {
        return new Money(calculateChange(lottoQuantity));
    }

    private long calculateChange(int lottoQuantity) {
        return money - ((long) lottoQuantity * LOTTO_PRICE);
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
