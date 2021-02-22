package lotto.domain;

import java.math.BigDecimal;
import lotto.utils.CustomException;
import lotto.utils.StringChecker;

public class Money {
    private final BigDecimal money;

    public Money(String moneyValue) {
        validateIsNumber(moneyValue);
        this.money = new BigDecimal(moneyValue);
    }

    private void validateIsNumber(String moneyValue) {
        if (!StringChecker.isNumber(moneyValue)) {
            throw new CustomException("구입금액은 숫자이어야 합니다.");
        }
    }

    public int getPossibleTicketCount() {
        return money.intValue() / LottoTicket.PRICE;
    }

}
