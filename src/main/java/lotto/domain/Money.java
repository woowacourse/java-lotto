package lotto.domain;

import lotto.utils.ValidationUtils;

public class Money {
    private static final int LOTTO_UNIT = 1000;

    private int money;


    public Money(String money) {
        ValidationUtils.validateIntegerNumberFormat(money);
        ValidationUtils.validatePositiveNumber(money);
        ValidationUtils.validateLottoUnit(money);

        this.money = Integer.parseInt(money);
    }

    public String changeMoney() {
        return String.valueOf(money % LOTTO_UNIT);
    }

    public String generateLottoTicketCount() {
        return String.valueOf(money / LOTTO_UNIT);
    }
}
