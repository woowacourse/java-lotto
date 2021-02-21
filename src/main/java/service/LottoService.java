package service;

import domain.LottoGameMachine;
import domain.bettingMoney.BettingMoney;
import domain.lotto.LottoTicket;
import domain.lotto.LottoTickets;

import java.util.List;

public class LottoService {
    private final LottoGameMachine lottoGameMachine;

    public LottoService(LottoGameMachine lottoGameMachine) {
        this.lottoGameMachine = lottoGameMachine;
    }

    public LottoTickets getLottoTickets(BettingMoney bettingMoney) {
        List<LottoTicket> lottoTickets = lottoGameMachine.buyTickets(bettingMoney);
        return new LottoTickets(lottoTickets);
    }
}
