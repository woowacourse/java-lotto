package lotterymachine.domain;

import lotterymachine.Money;

public class Count {
    private final int value;

    public Count(Money money) {
        this.value = money.getValue() / Money.PER_LOTTERY_TICKET_PRICE;
    }

    public int getValue() {
        return this.value;
    }
}
