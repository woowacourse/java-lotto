package service;

import domain.LottoMachine;
import domain.bettingMoney.BettingMoney;
import domain.lotto.LottoTicket;
import domain.lotto.LottoTickets;

import java.util.List;

public class LottoService {
    private final LottoMachine lottoMachine;

    public LottoService(LottoMachine lottoMachine) {
        this.lottoMachine = lottoMachine;
    }

    public LottoTickets getLottoTickets(BettingMoney bettingMoney) {
        List<LottoTicket> lottoTickets = lottoMachine.buyTickets(bettingMoney);
        return new LottoTickets(lottoTickets);
    }
}
