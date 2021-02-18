package lotto.domain;

import java.util.Objects;
import lotto.exception.LottoCustomException;

public class Money {
    private static final int MIN_MONEY_UNIT = 1000;
    public static final String MONEY_LESS_THAN_MIN_MONEY_ERROR_MESSAGE = "입력 금액은 1000원 이상이어야 합니다.";

    private final int money;

    public Money(int money) {
        validateMoneyLimit(money);
        this.money = money;
    }

    private void validateMoneyLimit(final int money) {
        if (money < MIN_MONEY_UNIT) {
            throw new LottoCustomException(MONEY_LESS_THAN_MIN_MONEY_ERROR_MESSAGE);
        }
    }

    public int countTickets() {
        return this.money / MIN_MONEY_UNIT;
    }

    public float calculateProfit(int totalReward) {
        return (float) totalReward / (float) money;
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
