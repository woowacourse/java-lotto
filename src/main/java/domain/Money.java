package domain;

public class Money {

    private final int amount;

    public Money(int amount) {
        validateNegative(amount);
        this.amount = amount;
    }

    public static Money forPurchaseAmount(int amount) {
        validatePurchaseAmount(amount);
        return new Money(amount);
    }

    private static void validatePurchaseAmount(int amount) {
        if (!LottoStore.isPurchasable(amount)) {
            throw new IllegalArgumentException("구매 금액은 로또 가격보다 적을 수 없습니다.");
        }
    }

    private void validateNegative(int amount) {
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
