package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Budget {
    private static final BigDecimal PRICE_AMOUNT_PER_LOTTO = BigDecimal.valueOf(1000);
    private static final int PROFIT_RATE_SCALE = 2;

    private final BigDecimal amount;

    public Budget(int amount) {
        this(BigDecimal.valueOf(amount));
    }

    private Budget(BigDecimal amount) {
        if (!isPositive(amount)) {
            throw new IllegalArgumentException("입력금은 반드시 양수여야 합니다.");
        }
        if (!isMultipleByLottoPrice(amount)) {
            throw new IllegalArgumentException("입력금은 반드시 1000의 배수여야 합니다.");
        }
        this.amount = amount;
    }

    public static Budget parse(String text) {
        return new Budget(Integer.parseInt(text));
    }

    private boolean isPositive(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }

    private boolean isMultipleByLottoPrice(BigDecimal moneyAmount) {
        return moneyAmount.remainder(PRICE_AMOUNT_PER_LOTTO).equals(BigDecimal.ZERO);
    }

    public BigDecimal getProfitRateFrom(BigDecimal totalPrize) {
        return totalPrize.divide(amount, PROFIT_RATE_SCALE, RoundingMode.HALF_DOWN);
    }

    public int getMaxCountForLottoIssue() {
        return amount.divide(PRICE_AMOUNT_PER_LOTTO, RoundingMode.DOWN).intValue();
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
