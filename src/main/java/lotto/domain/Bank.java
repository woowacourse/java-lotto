package lotto.domain;

public class Bank {
    private int usedMoney = 0;

    public void pay(final int money) {
        validateMoney(money);
        usedMoney += money;
    }

    private void validateMoney(final int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("금액을 지불해주십시오.");
        }
    }

    public int getUsedMoney() {
        return usedMoney;
    }
}
