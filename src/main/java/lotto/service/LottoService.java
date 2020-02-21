package lotto.service;

import lotto.domain.ticket.BettingMoney;
import lotto.domain.ticket.LottoTicketBundle;
import lotto.domain.ticket.strategy.LottoMachine;

public class LottoService {

    private final LottoMachine lottoMachine;

    public LottoService(LottoMachine lottoMachine) {
        this.lottoMachine = lottoMachine;
    }

    public LottoTicketBundle getLottoTicketBundle(BettingMoney bettingMoney) {
        return new LottoTicketBundle(lottoMachine.buyTickets(bettingMoney));
    }

}
