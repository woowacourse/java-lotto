package lotto.domain;

public class Money {

    private static final int LOTTO_PRICE = 1000;
    private final int money;

    public Money(int money) {
        if (!isValidMoney(money)) {
            throw new InvalidMoneyException("잘못된 금액 입니다.");
        }
        this.money = money;
    }

    private boolean isValidMoney(int money) {
        return (money > 0) && (money % 1000 == 0);
    }

    public int countOfLotto() {
        return this.money / LOTTO_PRICE;
    }

}
