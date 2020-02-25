package lotto.domain;

import lotto.util.InputValidationUtil;

public class Money {
    private static final int LOTTO_PURCHASE_UNIT = 1000;
    private static final String NOT_BUY_LOTTO_TICKET_EXCEPTION = "한개도 구매할 수 없습니다. %d원을 반환합니다.";

    private long money;

    public Money(String money) {
        this.money = InputValidationUtil.returnNumberWithNumberCheck(money);
        InputValidationUtil.isPositiveNumber(this.money);
        validateLottoUnit();
    }

    private void validateLottoUnit() {
        if (this.money < LOTTO_PURCHASE_UNIT) {
            throw new IllegalArgumentException(String.format(NOT_BUY_LOTTO_TICKET_EXCEPTION,this.money));
        }
    }
    //int 값
    public String lottoTicketCount() {
        return String.valueOf (this.money / LOTTO_PURCHASE_UNIT);
    }

    public int giveChangeMoney() {
        return (int) (this.money % LOTTO_PURCHASE_UNIT);
    }

    public long getMoney() {
        return money;
    }
}
