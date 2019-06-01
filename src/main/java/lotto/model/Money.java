package lotto.model;

public class Money {
    private static final int PRICE_OF_LOTTO = 1000;

    private int money;

    public Money(String input) {
        int currentMoney = Integer.parseInt(input);
        //TODO 상수 빼기
        if (currentMoney < PRICE_OF_LOTTO || currentMoney % PRICE_OF_LOTTO != 0) {
            throw new IllegalMoneyException();
        }
        this.money = Integer.parseInt(input);
    }


    public double calculatePercentage(double sum) {
        return sum/money;
    }

    public int getLottoCount() {
        return this.money/PRICE_OF_LOTTO;
    }

    public boolean isInputBiggerThanMoney(int input) {
        return input > money;
    }

    public void deduct(int count) {
        money = money - (count*PRICE_OF_LOTTO);
    }
}
