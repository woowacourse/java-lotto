package domain;

public class Profit {
    private final double profit;

    public Profit(Money budget, Money reward) {
        this.profit = reward.toInteger() / (double) budget.toInteger();
    }

    public double toDouble() {
        return this.profit;
    }
}
