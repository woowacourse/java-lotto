package lotto.domain;

import java.util.List;
import lotto.utils.LottoGenerator;

public class Machine {
    private final LottoTickets lottoTickets;

    public Machine(String moneyValue, LottoGenerator lottoGenerator) {
        final Money money = new Money(moneyValue);
        this.lottoTickets = new LottoTickets(money.getPossibleTicketCount(), lottoGenerator);
    }

    public List<LottoTicket> getTickets() {
        return lottoTickets.getLottoTickets();
    }

    public Result getResult(String winningNumbersValue, String bonusBallValue) {
        return new Result(winningNumbersValue, bonusBallValue, lottoTickets);
    }

}
