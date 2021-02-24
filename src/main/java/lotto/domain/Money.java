package lotto.domain;

import lotto.controller.LottoStore;
import lotto.exception.MoneyException;

public class Money {

    public static final String NOT_ENOUGH_MONEY = "로또를 사기에 금액이 모자랍니다.";
    public static final String EXCESS_LOTTO_PIECES = "구매 가능한 매수를 초과하였습니다.";
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
            throw new MoneyException(NOT_ENOUGH_MONEY);
        }
    }
}
