package domain.budget;

import java.math.BigDecimal;

public class Budget {
    public static final int ZERO = 0;

    private final BigDecimal amount;

    public Budget(final BigDecimal amount) {
        validateMoney(amount);
        this.amount = amount;
    }

    public static Budget amounts(int amount) {
        return new Budget(BigDecimal.valueOf(amount));
    }

    private void validateMoney(final BigDecimal money) {
        if (money.scale() > ZERO) {
            throw new IllegalArgumentException("자연수만 입력 가능합니다.");
        }
        if (money.intValue() < ZERO) {
            throw new IllegalArgumentException("1000원 이상의 수만 가능합니다.");
        }
    }

    public Budget divide(Budget amount) {
        return new Budget(this.amount.divide(amount.amount));
    }

    public int getIntValue() {
        return amount.intValue();
    }
}
