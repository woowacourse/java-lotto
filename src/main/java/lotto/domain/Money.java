package lotto.domain;

import lotto.util.Validation;

public class Money {
    private static final int LOTTO_UNIT = 1000;
    private int money;

    public Money(String money){
        Validation.validateIntegerNumberFormat(money);
        Validation.validatePositiveNumber(money);
        Validation.validateLottoUnit(money);

        this.money = Integer.parseInt(money);
    }

    public int changeMoney(){
        return money % LOTTO_UNIT;
    }

    public int generateLottoTicketCount(){
        return money / LOTTO_UNIT;
    }
}
