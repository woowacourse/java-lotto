package lotto.domain;

public class Money {
    private static final int LOTTO_PRICE = 1000;
    private final int money;

    public Money(int money) {
        if (money < LOTTO_PRICE) {
            throw new IllegalArgumentException("로또 1장의 가격은 1,000원 입니다.");
        }
        this.money = money;
    }

    public int purchaseLotto() {
        return money / LOTTO_PRICE;
    }
}
