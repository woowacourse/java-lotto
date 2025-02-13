package domain;

public record Money(
        int amount
) {

    public Money(String rawAmount) {
        this(Integer.parseInt(rawAmount));
    }

    public Money {
        validateNegative(amount);
    }

    public void validateNegative(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("돈은 음수가 될 수 없습니다.");
        }
    }

    public boolean isGreaterOrEqualThan(Money money) {
        return this.amount >= money.amount;
    }

    public Money minus(Money lottoPrice) {
        return new Money(this.amount - lottoPrice.amount);
    }

    public Money multiply(int count) {
        return new Money(this.amount * count);
    }

    public Money sum(Money money) {
        return new Money(this.amount + money.amount);
    }

    public double divide(Money purchaseLottoMoney) {
        return (double) this.amount / purchaseLottoMoney.amount;
    }

    public int getAmount() {
        return amount;
    }
}
