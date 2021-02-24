package service;

import domain.RandomLottoMachine;
import domain.bettingMoney.BettingMoney;
import domain.lotto.LottoTicket;
import domain.lotto.LottoTickets;
import domain.lotto.TicketCount;

import java.util.List;

public class LottoService {
    private final RandomLottoMachine randomLottoMachine;

    public LottoService(RandomLottoMachine randomLottoMachine) {
        this.randomLottoMachine = randomLottoMachine;
    }

    public LottoTickets getLottoTickets(BettingMoney bettingMoney) {
        int ticketCount = bettingMoney.getTicketCount();
        List<LottoTicket> lottoTickets = randomLottoMachine.makeTickets(ticketCount);
        return new LottoTickets(lottoTickets);
    }
}
