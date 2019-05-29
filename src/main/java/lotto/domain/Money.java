package lotto.domain;

public class Money {
    private static final int MIN_LOTTO_PRICE = 1000;
    private static final int MAX_LOTTO_PRICE = 1_000_000_000;
    private static final int MIN_PRICE = 1;

    private final int money;

    public Money(final int money) {
        checkInvalidMoney(money);
        this.money = money;
    }

    private void checkInvalidMoney(final int money) {
        if (money < MIN_LOTTO_PRICE || money > MAX_LOTTO_PRICE) {
            throw new IllegalArgumentException("로또 구입 가능 범위를 벗어났습니다.");
        }
    }

    public int calculateNumberOfPurchases(final int price) {
        if (price < MIN_PRICE) {
            throw new IllegalArgumentException("가격은 " + MIN_PRICE + "원 이상이여야 됩니다.");
        }
        return this.money / price;
    }
}
