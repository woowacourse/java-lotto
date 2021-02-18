package lotto.domain;

import lotto.exception.LottoCustomException;

import java.util.Objects;

public class Money {

    private static final int MONEY_UNIT = 1000;

    private final int money;

    public Money(int money) {
        validateMoneyLimit(money);
        this.money = money;
    }

    public int getTicketCount() {
        return this.money / MONEY_UNIT;
    }

    public float getProfit(int totalReward) {
        return (float) totalReward / (float) money;
    }

    public int getChange() {
        return money % MONEY_UNIT;
    }

    private void validateMoneyLimit(final int money) {
        if (money < MONEY_UNIT) {
            throw new LottoCustomException("입력 금액은 1000원 이상이어야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money1 = (Money) o;
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
