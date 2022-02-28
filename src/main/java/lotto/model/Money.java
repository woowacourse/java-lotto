package lotto.model;

import lotto.util.InputValidator;

public class Money {

    private final int amount;

    public Money(int amount) {
        this.amount = InputValidator.validatePurchaseAmount(amount);
    }

    public int purchase(int price) {
        return amount / price;
    }
}
