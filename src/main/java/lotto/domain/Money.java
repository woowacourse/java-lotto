package lotto.domain;

public class Money {

    private static final int LOTTO_PRICE = 1000;
    private final int money;

    public Money(int money) {
        this.money = money;
        checkMinimumMoney();
    }

    private void checkMinimumMoney() {
        if (money < LOTTO_PRICE) {
            throw new IllegalArgumentException("로또의 최소 가격은 1,000원 입니다. 그 이상을 입력해주세요!");
        }
    }

    public int getNumberOfLotto() {
        return money / LOTTO_PRICE;
    }
}
