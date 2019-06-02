package lotto.domain;

import lotto.domain.exception.InvalidLottoPriceException;

import java.util.Objects;

public class Cash {
    private static final String LOWER_CASH_ERROR_MESSAGE = "금액은 양의 정수입니다.";
    private static final int LOWER_BOUND_OF_CASH = 0;
    private final int money;

    public Cash(int money) {
        if (money <= LOWER_BOUND_OF_CASH) {
            throw new InvalidLottoPriceException(LOWER_CASH_ERROR_MESSAGE);
        }

        this.money = money;
    }

    public boolean isLittleThan(int money) {
        return this.money < money;
    }

    public int calculateQuotient(int money) {
        return this.money / money;
    }

    public int calculateRemainder(int money) {
        return this.money % money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Cash cash1 = (Cash) o;
        return money == cash1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
