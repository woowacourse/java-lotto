package lotto.domain;

import lotto.exception.LottoPiecesException;

public class Money {
    private final int money;

    public Money(int money) {
        this.money = money;
    }

    public int getLottoPieces(int lottoPrice) {
        checkEnoughMoney(money, lottoPrice);
        return money / lottoPrice;
    }

    private void checkEnoughMoney(int possessedMoney, int lottoPrice) {
        if (possessedMoney < lottoPrice) {
            throw new LottoPiecesException("로또를 사기에 금액이 모자랍니다.");
        }
    }
}
