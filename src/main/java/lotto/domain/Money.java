package lotto.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lotto.utils.CustomException;
import lotto.utils.StringChecker;

public class Money {
    private final long ticketCount;
    private final BigDecimal change;

    public Money(String moneyValue) {
        this(moneyValue, new ArrayList<>());
    }

    public Money(String moneyValue, List<LottoTicket> lottoTickets) {
        validateIsNumber(moneyValue);
        validateNotOverMoney(moneyValue, lottoTickets);
        final BigDecimal money = new BigDecimal(moneyValue);
        this.ticketCount = money.divideToIntegralValue(new BigDecimal(LottoTicket.PRICE)).intValue()
            - lottoTickets.size();
        this.change = money
            .subtract(BigDecimal.valueOf((lottoTickets.size() + ticketCount) * LottoTicket.PRICE));
    }

    private void validateNotOverMoney(String moneyValue, List<LottoTicket> lottoTickets) {
        if (Long.valueOf(moneyValue) < lottoTickets.size() * LottoTicket.PRICE) {
            throw new CustomException("수동발행이 구입가능금액을 넘어 발행이 취소됩니다.");
        }
    }

    private void validateIsNumber(String moneyValue) {
        if (!StringChecker.isNumber(moneyValue)) {
            throw new CustomException("구입금액은 숫자이어야 합니다.");
        }
    }

    public int getPossibleTicketCount() {
        return (int) ticketCount;
    }

    public int getChange() {
        return change.intValue();
    }

}
