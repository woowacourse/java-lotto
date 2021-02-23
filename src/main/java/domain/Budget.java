package domain;

public class Budget {
    private static final String ERROR_NOT_ENOUGH_BUDGET = "[ERROR] 자금이 부족합니다.";

    private Money budget;

    public Budget(Money money) {
        this.budget = money;
    }

    public boolean canAfford(Money pricePerItems, int quantity) {
        return this.budget.toLong() >= pricePerItems.multiply(quantity).toLong();
    }

    public Money remain() {
        return this.budget;
    }

    public void buy(Money pricePerItems, int quantity) throws IllegalArgumentException {
        try {
            this.budget = this.budget.subtract(pricePerItems.multiply(quantity));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ERROR_NOT_ENOUGH_BUDGET);
        }
    }
}
