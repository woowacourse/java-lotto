package lotto.service;

import lotto.domain.ticket.BettingInfo;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTicketBundle;
import lotto.domain.ticket.manual.ManualNumber;
import lotto.domain.ticket.manual.ManualNumberBundle;
import lotto.domain.ticket.strategy.LottoMachine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoService {
    private static final List<ManualNumber> RANDOM_NUMBERS = Collections.emptyList();

    private final LottoMachine randomLottoMachine;
    private final LottoMachine manualLottoMachine;

    public LottoService(LottoMachine randomLottoMachine, LottoMachine manualLottoMachine) {
        this.randomLottoMachine = randomLottoMachine;
        this.manualLottoMachine = manualLottoMachine;
    }

    public LottoTicketBundle getLottoTicketBundle(BettingInfo bettingInfo, ManualNumberBundle manualNumberBundle) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        lottoTickets.addAll(makeManualLottoTickets(bettingInfo, manualNumberBundle));
        lottoTickets.addAll(makeRandomLottoTickets(bettingInfo));

        return new LottoTicketBundle(lottoTickets);
    }

    private List<LottoTicket> makeRandomLottoTickets(BettingInfo bettingInfo) {
        return randomLottoMachine.buyTickets(bettingInfo.getRandomAmount(), RANDOM_NUMBERS);
    }

    private List<LottoTicket> makeManualLottoTickets(BettingInfo bettingInfo, ManualNumberBundle manualNumberBundle) {
        return manualLottoMachine.buyTickets(bettingInfo.getManualAmount(), manualNumberBundle.getManualNumbers());
    }

}
