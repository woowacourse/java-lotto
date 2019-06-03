package lotto.model;

import lotto.model.exceptions.IllegalMoneyException;

public class Money {
    private static final int PRICE_OF_LOTTO = 1000;
    private static final int DIVISION_CONDITION = 0;

    private final int money;

    public Money(String input) {
        int currentMoney = Integer.parseInt(input);
        if (currentMoney < PRICE_OF_LOTTO || currentMoney % PRICE_OF_LOTTO != DIVISION_CONDITION) {
            throw new IllegalMoneyException();
        }
        this.money = currentMoney;
    }


    public double calculateProfitRate(double sum) {
        return sum / money;
    }


    public boolean isInputBiggerThanMoney(int input) {
        int comparison = input * PRICE_OF_LOTTO;
        return (comparison) > this.money;
    }

    public int calculateAutomatiLottoCount(int count) {
        return (money - (count * PRICE_OF_LOTTO)) / PRICE_OF_LOTTO;
    }
}
