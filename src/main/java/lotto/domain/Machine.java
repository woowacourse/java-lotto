package lotto.domain;

import java.util.List;
import lotto.utils.LottoGenerator;

public class Machine {
    private final Money money;
    private final LottoTickets lottoTickets;

    public Machine(String moneyValue, LottoGenerator lottoGenerator) {
        money = new Money(moneyValue);
        this.lottoTickets = new LottoTickets(money.getPossibleTicketCount(), lottoGenerator);
    }

    public List<LottoTicket> getTickets() {
        return lottoTickets.getLottoTickets();
    }

    public int getChange() {
        return money.getChange();
    }

    public Result getResult(String winningNumbersValue, String bonusBallValue) {
        return new Result(winningNumbersValue, bonusBallValue, lottoTickets);
    }

}
