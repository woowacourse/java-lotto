package lotto.domain;

import lotto.domain.exception.LackOfMoneyException;

public class LottoMachine {
    public static final int LOTTO_MONEY = 1000;

    private int remainMoney;

    LottoMachine(final int money) {
        if (money < LOTTO_MONEY) {
            throw new LackOfMoneyException("1000원 이상 구매하세요");
        }
        remainMoney = money;
    }

    void buy() {
        this.remainMoney -= LOTTO_MONEY;
    }

    boolean isRemainMoney() {
        return (this.remainMoney >= LOTTO_MONEY);
    }
}
