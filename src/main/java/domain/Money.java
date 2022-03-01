package domain;

import java.util.Objects;

import static constant.LottoConstant.LOTTO_TICKET_PRICE;

public class Money {

    private static final int MINIMUM_COIN_PRICE = 10;

    private final int amount;

    public Money(int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    public boolean isPurchasable(int amount) {
        return this.amount >= amount;
    }

    public int getPurchasableNumber() {
        return this.amount / LOTTO_TICKET_PRICE;
    }


    public void validateManualCount(int manualCount) {
        if (manualCount > getPurchasableNumber()) {
            throw new IllegalArgumentException("구매하려는 로또가 보유 금액을 초과했습니다.");
        }
    }

    private void validateAmount(int amount) {
        validateUnit(amount);
        validateMinimumAmount(amount);

    }

    private void validateUnit(int amount) {
        if (amount < LOTTO_TICKET_PRICE) {
            throw new IllegalArgumentException("금액은 1000원 이상이어야 합니다.");
        }
    }

    private void validateMinimumAmount(int amount) {
        if (amount % MINIMUM_COIN_PRICE != 0) {
            throw new IllegalArgumentException("금액은 10 단위로 나누어 떨어져야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
