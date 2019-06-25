package domain.money;

public class Money {
    private static final int MINIMUM_AMOUNT = 0;
    protected int amountOfMoney;

    protected Money(int amountOfMoney) {
        validateAmount(amountOfMoney);
        this.amountOfMoney = amountOfMoney;
    }

    private static void validateAmount(int amountOfMoney) {
        if (amountOfMoney < MINIMUM_AMOUNT) {
            throw new IllegalMoneyAmountException();
        }
    }

    public static Money valueOf(int amountOfMoney) {
        return new Money(amountOfMoney);
    }

    public int getAmount() {
        return amountOfMoney;
    }

    public Money getChangeOf(Money money) {
        return new Money(this.amountOfMoney - money.getAmount());
    }
}
