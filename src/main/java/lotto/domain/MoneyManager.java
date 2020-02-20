package lotto.domain;

public class MoneyManager {
    private static final int LOTTO_PRICE = 1000;

    private int money;

    public MoneyManager(int money) {
        purchaseException(money);
        this.money = money;
    }

    public int purchase() {
        return money / LOTTO_PRICE;
    }

    static void purchaseException(int money) {
        if (money < LOTTO_PRICE) {
            throw new IllegalArgumentException("돈이 부족해 로또를 살 수 없습니다.");
        }
    }

    public int getMoney() {
        return money;
    }
}
