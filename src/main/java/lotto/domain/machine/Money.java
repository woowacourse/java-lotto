package lotto.domain.machine;

import lotto.domain.machine.exeption.InvalidMinimumMoneyException;

public class Money {
    private static final int LOTTO_PRICE = 1000;
    private final int money;

    private Money(int money) {
        this.money = money;
    }

    public static Money of(int money) {
        validateMinumumMoney(money);
        return new Money(money);
    }

    private static void validateMinumumMoney(int money) {
        if (money < LOTTO_PRICE) {
            throw new InvalidMinimumMoneyException("1장 이상 구입 가능한 금액을 넣어주세요.");
        }
    }

    public static int getLottoPrice() {
        return LOTTO_PRICE;
    }

    public int getWholeTicketQuantity() {
        return this.money / LOTTO_PRICE;
    }

    public int getRest() {
        return this.money % LOTTO_PRICE;
    }
}
