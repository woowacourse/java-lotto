package domain;

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

    static Money valueOf(int amountOfMoney) {
        return new Money(amountOfMoney);
    }

    int getAmount() {
        return amountOfMoney;
    }

    public Money getChangeOf(Money money) {
        return new Money(this.amountOfMoney - money.getAmount());
    }
}
