package model;

import java.math.BigDecimal;
import java.util.Objects;
import util.NumberFormatStringParser;

public class Budget {
    private static final BigDecimal PRICE_AMOUNT_PER_LOTTO = BigDecimal.valueOf(1000);
    public static final Budget ZERO = new Budget(BigDecimal.ZERO);

    private final BigDecimal amount;

    public Budget(int amount) {
        this(BigDecimal.valueOf(amount));
    }

    private Budget(BigDecimal amount) {
        if (!isMultipleByLottoPrice(amount)) {
            throw new IllegalArgumentException("입력금은 반드시 1000의 배수여야 합니다.");
        }
        this.amount = amount;
    }

    public static Budget parse(String text) {
        int moneyAmount = NumberFormatStringParser.parse(text);
        return new Budget(moneyAmount);
    }

    private static boolean isMultipleByLottoPrice(BigDecimal moneyAmount) {
        return moneyAmount.remainder(PRICE_AMOUNT_PER_LOTTO).equals(BigDecimal.ZERO);
    }

    public Budget add(Budget prize) {
        return new Budget(this.amount.add(prize.amount));
    }

    public Budget multiply(int factor) {
        return new Budget(this.amount.multiply(BigDecimal.valueOf(factor)));
    }

    public BigDecimal divide(Budget budget) {
        return this.amount.divide(budget.amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Budget budget = (Budget) o;
        return Objects.equals(amount, budget.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
