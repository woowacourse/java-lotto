package lotto.domain;

import lotto.receiver.MoneyReceiver;

public class Money {

    private final int money;

    public Money(String input) {
        this.money = MoneyReceiver.receive(input);
    }

    public int getMoney() {
        return money;
    }
}
