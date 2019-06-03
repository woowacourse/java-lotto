package lotto.domain;

public class Money {
    private static final int PRICE_PER_LOTTO = 1000;

    private final long money;

    private Money(final long money) {
        validate(money);
        this.money = money;
    }

    public static Money from(final long money) {
        return new Money(money);
    }

    public static Money from(final String money) {
        return from(Integer.parseInt(money));
    }

    private static void validate(final long money) {
        if (money % PRICE_PER_LOTTO != 0 || money < PRICE_PER_LOTTO) {
            throw new IllegalArgumentException("로또 구입 금액은" + PRICE_PER_LOTTO + "단위만 입력 가능합니다.");
        }
    }

    public long value() {
        return money;
    }

    public int getCountOfPurchase() {
        return (int) money / PRICE_PER_LOTTO;
    }
}
