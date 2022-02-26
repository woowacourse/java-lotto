package lotterymachine.model;

public class Money {
    private final int amount;

    public Money(int amount) {
        this.amount = amount;
    }

    public static Money from(int amount) {
        return new Money(amount);
    }

    public double divide(Money money) {
        return (double) this.amount / money.amount;
    }

    public int getAmount() {
        return amount;
    }
}
