package lotto.domain;

import lotto.domain.exception.AmountDeficientException;
import lotto.domain.exception.LottoNotBuyException;

public class Money {
    private static final int LOTTO_PRICE = 1000;
    private static final int LOTTO_MAX_PRICE = 100_000;
    private final int money;

    public Money(int money) {
        if (money < LOTTO_PRICE) {
            throw new AmountDeficientException("로또 1장의 가격은 1,000원 입니다.");
        }
        if (money > LOTTO_MAX_PRICE) {
            throw new AmountDeficientException("구매 가능한 금액은 100,000원 미만 입니다.");
        }
        this.money = money;
    }

    public int countOfLotto() {
        return money / LOTTO_PRICE;
    }

    public int checkPurchaseLotto(int count) {
        if (countOfLotto() < count) {
            throw new LottoNotBuyException("금액이 부족하여 구입하지 못합니다.");
        }
        return count;
    }

    public int getAutoLottoNumber(int manualLottoNumber) {
        return (money - (manualLottoNumber * LOTTO_PRICE)) / LOTTO_PRICE;
    }

    public int getMoney() {
        return money;
    }
}