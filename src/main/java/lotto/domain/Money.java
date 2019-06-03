package lotto.domain;

public class Money {

    private static final int LOTTO_PRICE = 1000;
    private static final int ZERO = 0;
    private final long money;

    public Money(long money) {
        if (!isValidMoney(money)) {
            throw new InvalidMoneyException("잘못된 금액 입니다.");
        }
        this.money = money;
    }

    public long getMoney() {
        return this.money;
    }

    private boolean isValidMoney(long money) {
        return (money > ZERO) && (money % LOTTO_PRICE == 0);
    }

    public int calculateCountOfLotto() {
        return (int) (this.money / LOTTO_PRICE);
    }

    public double prizeDivideMoney(long prize) {
        return prize / money;
    }
}
