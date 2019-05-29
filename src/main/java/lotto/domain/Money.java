package lotto.domain;

public class Money {
    private static final int LOTTO_PRICE = 1_000;

    private final int money;

    public Money(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        if (money < LOTTO_PRICE) {
            throw new IllegalArgumentException(LOTTO_PRICE + "원 이상의 금액을 입력하세요");
        }
    }

    public int getLottoSize() {
        return money / LOTTO_PRICE;
    }

    public int getMoney() {
        return money;
    }
}
