package lotto.domain;

import java.math.BigDecimal;
import java.util.List;
import lotto.utils.CustomException;
import lotto.utils.StringChecker;

public class Money {
    private final BigDecimal money;
    private final int ticketCount;
    private final BigDecimal change;

    public Money(String moneyValue) {
        validateIsNumber(moneyValue);
        this.money = new BigDecimal(moneyValue);
        this.ticketCount = money.divideToIntegralValue(new BigDecimal(LottoTicket.PRICE))
            .intValue();
        this.change = money.subtract(BigDecimal.valueOf(LottoTicket.PRICE * ticketCount));
    }

    public Money(String moneyValue, List<LottoTicket> lottoTickets) {
        validateIsNumber(moneyValue);
        this.money = new BigDecimal(moneyValue);
        this.ticketCount =
            money.divideToIntegralValue(new BigDecimal(LottoTicket.PRICE)).intValue() - lottoTickets
                .size();
        this.change = money.subtract(BigDecimal.valueOf(LottoTicket.PRICE * ticketCount));
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
