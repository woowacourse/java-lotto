package lotto.domain;

import java.util.Objects;

public class Money {
    private static final int LOTTO_PRICE = 1000;
    public static final String NOT_ENOUGH_MONEY_ERROR = String.format("%d원 이상의 금액만 입력 가능합니다.", LOTTO_PRICE);
    public static final String MANUAL_LOTTO_QUANTITY_ERROR = "수동 로또를 살 돈이 모자랍니다.";

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
        return new Money(calculateAffordableLottoQuantity() * LOTTO_PRICE);
    }

    public int calculateAffordableLottoQuantity() {
        return money / LOTTO_PRICE;
    }

    public float calculateProfitRate(Money profit) {
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

    private int calculateChange(int lottoQuantity) {
        return money - (lottoQuantity * LOTTO_PRICE);
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
