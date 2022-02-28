package domain;

import java.util.Objects;

import static constant.LottoConstant.LOTTO_TICKET_PRICE;

public class Money {

    private static final int MINIMUM_AMOUNT = 10;
    private static final String MONEY_MUST_BE_DIVIDABLE_BY_TEN = "금액은 10 단위로 나누어 떨어져야 합니다.";

    private final int amount;

    private Money(int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    public static Money from(int amount) {
        return new Money(amount);
    }

    public boolean isPurchasable(int amount) {
        return this.amount >= amount;
    }

    public int getPurchasableNumber(int amount) {
        return this.amount / amount;
    }

    private void validateAmount(int amount) {
        if (amount % MINIMUM_AMOUNT != 0) {
            throw new IllegalArgumentException(MONEY_MUST_BE_DIVIDABLE_BY_TEN);
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

    public void createLottoType(int manualCount) {
        validateManualCount(manualCount);
    }

    private void validateManualCount(int manualCount) {
        if (manualCount > getPurchasableNumber(LOTTO_TICKET_PRICE)) {
            throw new IllegalArgumentException("구매하려는 로또가 보유 금액을 초과했습니다.");
        }
    }
}
