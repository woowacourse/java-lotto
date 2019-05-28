package lotto.domain;

public class Money {
    private final int money;

    public Money(int money) {
        this.money = money;
        checkMinimumMoney();
    }

    private void checkMinimumMoney() {
        if (money < 1000) {
            throw new IllegalArgumentException("로또의 최소 가격은 1,000원 입니다. 그 이상을 입력해주세요!");
        }
    }

    public int getNumberOfLotto() {
        return money / 1000;
    }
}
