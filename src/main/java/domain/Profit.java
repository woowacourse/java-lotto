package domain;

public class Profit {
    private final double profit;

    public Profit(Money budget, Money reward) {
        this.profit = reward.toLong() / (double) budget.toLong();
    }

    public double toDouble() {
        return this.profit;
    }
}
