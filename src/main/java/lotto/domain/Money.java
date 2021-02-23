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
        return new Money(getAffordableLottoQuantity().getTotalPrice());
    }

    public LottoQuantity getAffordableLottoQuantity() {
        return new LottoQuantity((int) money / LOTTO_PRICE);
    }

    public float getProfitRate(Money profit) {
        return (float) profit.money / this.money;
    }

    public void validateAffordabilityOf(LottoQuantity lottoQuantity) {
        if (!isAffordableToBuy(lottoQuantity)) {
            throw new IllegalArgumentException(MANUAL_LOTTO_QUANTITY_ERROR);
        }
    }

    private boolean isAffordableToBuy(LottoQuantity lottoQuantity) {
        return 0 <= calculateChangeAfterBuying(lottoQuantity);
    }

    public Money getChangeAfterBuying(LottoQuantity lottoQuantity) {
        return new Money(calculateChangeAfterBuying(lottoQuantity));
    }

    private long calculateChangeAfterBuying(LottoQuantity lottoQuantity) {
        return money - lottoQuantity.getTotalPrice();
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
