package lotto.domain;

import java.math.BigDecimal;
import java.util.regex.Pattern;
import lotto.utils.CustomException;

public class Money {
    private static final String IS_NUMBER = "\\d+";

    private final BigDecimal money;

    public Money(String moneyValue) {
        validateIsNumber(moneyValue);
        this.money = new BigDecimal(moneyValue);
    }

    private void validateIsNumber(String moneyValue) {
        if (!Pattern.matches(IS_NUMBER, moneyValue)) {
            throw new CustomException("구입금액은 숫자이어야 합니다.");
        }
    }

    public int getPossibleTicketCount() {
        return money.intValue() / LottoTicket.PRICE;
    }

}
