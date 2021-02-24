package domain;

public class Quantity {
    private static final long MINIMUM_RANGE = 0;
    private static final String ERROR_NEGATIVE_COUNT = "[ERROR] 양수만 입력해주세요.";
    private static final String ERROR_OUT_OF_BUDGET = "[ERROR] 구입금액으로 살 수 있는 최대 개수를 초과했습니다.";

    private final long manualCount;
    private final long autoCount;

    public Quantity(Money budget, long manualCount) {
        validatePositiveCount(manualCount);
        validateWithinBudget(budget, manualCount);
        this.manualCount = manualCount;
        this.autoCount = budget.calculateTotalQuantity() - this.manualCount;
    }

    private void validatePositiveCount(long manualCount) {
        if (manualCount < MINIMUM_RANGE) {
            throw new IllegalArgumentException(ERROR_NEGATIVE_COUNT);
        }
    }

    private void validateWithinBudget(Money budget, long manualCount) {
        if (budget.calculateTotalQuantity() < manualCount) {
            throw new IllegalArgumentException(ERROR_OUT_OF_BUDGET);
        }
    }

    public long manual() {
        return this.manualCount;
    }

    public long auto() {
        return this.autoCount;
    }
}
