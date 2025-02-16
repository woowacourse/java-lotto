package lotto.domain;

public class Bank {
    private int usedMoney = 0;

    public void pay(final int money) {
        usedMoney += money;
    }

    public int getUsedMoney() {
        return usedMoney;
    }
}
