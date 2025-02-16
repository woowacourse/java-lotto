package lotto.domain;

public class Bank {
    private int usedMoney = 0;

    public void pay(int money) {
        usedMoney += money;
    }

    public int getUsedMoney() {
        return usedMoney;
    }
}
