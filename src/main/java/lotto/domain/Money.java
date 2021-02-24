package lotto.domain;

import java.math.BigDecimal;
import lotto.utils.CustomException;
import lotto.utils.StringChecker;

public class Money {
    private final int ticketCount;
    private final BigDecimal change;

    public Money(String moneyValue) {
        this(moneyValue, 0);
    }

    public Money(String moneyValue, int analogTicketCount) {
        validateIsNumber(moneyValue);
        validateNotOverMoney(moneyValue, analogTicketCount);
        final BigDecimal money = new BigDecimal(moneyValue);
        this.ticketCount = money.divideToIntegralValue(new BigDecimal(LottoTicket.PRICE)).intValue()
            - analogTicketCount;
        this.change = money
            .subtract(
                BigDecimal.valueOf((long) (analogTicketCount + ticketCount) * LottoTicket.PRICE));
    }

    private void validateNotOverMoney(String moneyValue, int analogTicketCount) {
        if (Integer.parseInt(moneyValue) < analogTicketCount * LottoTicket.PRICE) {
            throw new CustomException("수동발행이 구입가능금액을 넘어 발행이 취소됩니다.");
        }
    }

    private void validateIsNumber(String moneyValue) {
        if (!StringChecker.isNumber(moneyValue)) {
            throw new CustomException("구입금액은 숫자이어야 합니다.");
        }
    }

    public int getPossibleTicketCount() {
        return ticketCount;
    }

    public int getChange() {
        return change.intValue();
    }

}
