package lotto.domain;

import java.util.Objects;

public class Money {

    private static final String ERROR_NEGATIVE_INPUT_MESSAGE = "금액은 음수를 입력하면 안됩니다.";
    private static final String ERROR_NOT_OVER_PAY_MESSAGE = "가지고 있는 돈을 초과해서 지불할 수 없습니다.";

    private int amount;

    public Money(int amount) {
        validateNegative(amount);
        this.amount = amount;
    }

    private void validateNegative(int number) {
        if (number < 0) {
            throw new IllegalArgumentException(ERROR_NEGATIVE_INPUT_MESSAGE);
        }
    }

    public int canBuyNumber(Money itemMoney) {
        return this.amount / itemMoney.amount;
    }

    public void pay(Money money){
        if(money.amount > this.amount){
            throw new IllegalArgumentException(ERROR_NOT_OVER_PAY_MESSAGE);
        }
        this.amount -= money.amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
