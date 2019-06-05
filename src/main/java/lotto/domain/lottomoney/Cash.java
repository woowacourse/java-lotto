package lotto.domain.lottomoney;

import java.util.Objects;

public class Cash {
    private static final String ERROR_LOWER_CASH_MESSAGE = "금액은 양의 정수입니다.";
    private static final int LOWER_BOUND_OF_CASH = 0;
    private final long money;

    public Cash(long money) {
        if (money <= LOWER_BOUND_OF_CASH) {
            throw new InvalidLottoPriceException(ERROR_LOWER_CASH_MESSAGE);
        }

        this.money = money;
    }

    public boolean isLittleThan(long money) {
        return this.money < money;
    }

    public long calculateQuotient(long money) {
        return this.money / money;
    }

    public long calculateRemainder(long money) {
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
