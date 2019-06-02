package lotto.domain;

import static lotto.domain.LottoRule.*;

public class Money {

    private final int money;

    public Money(int money) {
        this.money = money;
        checkMinimumMoney();
    }

    private void checkMinimumMoney() {
        if (money < MONEY_PER_LOTTO.get()) {
            throw new IllegalArgumentException("로또의 최소 가격은 1,000원 입니다. 그 이상을 입력해주세요!");
        }
    }

    public int getNumberOfLotto() {
        return money / MONEY_PER_LOTTO.get();
    }

    public boolean isOverTotalNumberOfLotto(int numberOfLotto) {
        return (getNumberOfLotto() < numberOfLotto);
    }
}
