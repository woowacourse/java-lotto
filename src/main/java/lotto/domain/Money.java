package lotto.domain;

import lotto.exception.MoneyException;

public class Money {

    public static final String NOT_ENOUGH_MONEY = "로또를 사기에 금액이 모자랍니다.";
    private final int money;

    public Money(int money) {
        checkEnoughMoney(money);
        this.money = money;
    }

    public int getLottoPieces() {
        return money / Lotto.PRICE;
    }

    private void checkEnoughMoney(int possessedMoney) {
        if (possessedMoney < Lotto.PRICE) {
            throw new MoneyException(NOT_ENOUGH_MONEY);
        }
    }
}
