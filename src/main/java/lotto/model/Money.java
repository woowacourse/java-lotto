package lotto.model;

public class Money {
    private static final int PRICE_OF_LOTTO = 1000;

    private final int money;

    public Money(String input) {
        int currentMoney = Integer.parseInt(input);
        //TODO 상수 빼기
        if (currentMoney < PRICE_OF_LOTTO || currentMoney % PRICE_OF_LOTTO != 0) {
            throw new IllegalMoneyException();
        }
        this.money = currentMoney;
    }


    public double calculatePercentage(double sum) {
        return sum/money;
    }

    public int getLottoCount() {
        return this.money/PRICE_OF_LOTTO;
    }

    public boolean isInputBiggerThanMoney(int input) {
        int comparison = input*PRICE_OF_LOTTO;
        return (comparison) > this.money;
    }

    public int calculateAutomatiLottoCount(int count) {
        return (money - (count*PRICE_OF_LOTTO))/PRICE_OF_LOTTO;
    }
}
