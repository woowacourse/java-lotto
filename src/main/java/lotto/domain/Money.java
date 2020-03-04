package lotto.domain;

public class Money {

    private static final int MIN_MONEY = 1000;
    private int money;

    public Money(int money) {
        validateMoney(money);
        this.money = money;
    }

    private void validateMoney(int money) {
        if (money < MIN_MONEY) {
            throw new IllegalArgumentException("금액이 부족해 로또를 구매할 수 없습니다.");
        }
    }

    public int getMoney() {
        return money;
    }
}
