package lotto.domain;

import lotto.domain.exception.LackOfMoneyException;

import java.util.List;

public class LottoMachine {
    public static final int LOTTO_MONEY = 1000;

    private LottoFactory lottoFactory;
    private int remainMoney;

    LottoMachine(final int money) {
        if (money < LOTTO_MONEY) {
            throw new LackOfMoneyException("1000원 이상 구매하세요");
        }
        lottoFactory = new LottoFactory();
        remainMoney = money;
    }

    Lotto buy(final List<Integer> lottoNumbers) {
        this.remainMoney -= LOTTO_MONEY;

        return lottoFactory.create(lottoNumbers);
    }

    boolean isRemainMoney() {
        return (this.remainMoney >= LOTTO_MONEY);
    }
}
