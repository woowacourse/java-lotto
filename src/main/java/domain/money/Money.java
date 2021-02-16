package domain.money;

import java.math.BigDecimal;

public class Money {
    public static final int ZERO = 0;

    private final BigDecimal money;

    public Money(final BigDecimal money) {
        validateMoney(money);
        this.money = money;
    }

    private void validateMoney(final BigDecimal money) {
        if (money.precision() > ZERO) {
            throw new IllegalArgumentException();
        }
        if (money.intValue() < ZERO) {
            throw new IllegalArgumentException();
        }
    }
}
