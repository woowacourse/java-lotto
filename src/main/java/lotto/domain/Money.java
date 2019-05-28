package lotto.domain;

public class Money {
    private static final int PRICE_PER_LOTTO = 1000;

    private final int money;

    private Money(final int money) {
        this.money = money;
    }

    public static Money from(final int money) {
        validate(money);
        return new Money(money);
    }

    private static void validate(final int money) {
        if (money % PRICE_PER_LOTTO != 0) {
            throw new IllegalArgumentException("로또 구입 금액은" + PRICE_PER_LOTTO + "단위만 입력 가능합니다.");
        }
    }

    public int getPurchaseCount() {
        return money / PRICE_PER_LOTTO;
    }
}
