package lotto.domain;

import lotto.exception.LottoPiecesException;

public class Money {

    private static final int LOTTO_PRICE = 1000;

    private final int money;

    public Money(int money) {
        checkEnoughMoney(money);
        this.money = money;
    }

    public int getLottoPieces() {
        return money / LOTTO_PRICE;
    }

    private void checkEnoughMoney(int possiblePieces) {
        if (possiblePieces < LOTTO_PRICE) {
            throw new LottoPiecesException("로또를 사기에 금액이 모자랍니다.");
        }
    }
}
