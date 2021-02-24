package lotto.domain;

import lotto.controller.LottoStore;
import lotto.exception.LottoPiecesException;

public class Money {
    private final int money;

    public Money(int money) {
        checkEnoughMoney(money);
        this.money = money;
    }

    public int getLottoPieces() {
        return money / LottoStore.LOTTO_PRICE;
    }

    private void checkEnoughMoney(int possessedMoney) {
        if (possessedMoney < LottoStore.LOTTO_PRICE) {
            throw new LottoPiecesException("로또를 사기에 금액이 모자랍니다.");
        }
    }
}
