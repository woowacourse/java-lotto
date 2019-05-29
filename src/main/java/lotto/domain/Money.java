package lotto.domain;

import lotto.domain.domainexception.InvalidMoneyException;

import static lotto.domain.domainconstants.DomainConstants.LOTTO_PRICE;

public class Money {
    private final int money;

    public Money(int insertMoney) {
        validMoney(insertMoney);
        this.money = insertMoney;
    }

    private void validMoney(int insertMoney) {
        checkMinimumMoney(insertMoney);
        checkUnit(insertMoney);
    }

    private void checkMinimumMoney(int insertMoney) {
        if (insertMoney < LOTTO_PRICE) {
            throw new InvalidMoneyException("최소 금액은 " + LOTTO_PRICE + "원 입니다.");
        }
    }

    private void checkUnit(int insertMoney) {
        if (insertMoney % LOTTO_PRICE != 0) {
            throw new InvalidMoneyException("금액이 " + LOTTO_PRICE + "원 단위가 아닙니다.");
        }
    }

    public int howManyLotto(){
        return money / LOTTO_PRICE;
    }
}
