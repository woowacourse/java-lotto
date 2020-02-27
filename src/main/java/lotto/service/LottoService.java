package lotto.service;

import lotto.domain.ticket.BettingInfo;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTicketBundle;
import lotto.domain.ticket.manual.ManualNumberBundle;
import lotto.domain.ticket.strategy.ManualLottoMachine;
import lotto.domain.ticket.strategy.RandomLottoMachine;

import java.util.ArrayList;
import java.util.List;

public class LottoService {

    public LottoTicketBundle getLottoTicketBundle(BettingInfo bettingInfo, ManualNumberBundle manualNumberBundle) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        lottoTickets.addAll(makeManualLottoTickets(bettingInfo, manualNumberBundle));
        lottoTickets.addAll(makeRandomLottoTickets(bettingInfo));

        return new LottoTicketBundle(lottoTickets);
    }

    private List<LottoTicket> makeRandomLottoTickets(BettingInfo bettingInfo) {
        return new RandomLottoMachine(bettingInfo.getRandomAmount())
                .buyTickets();
    }

    private List<LottoTicket> makeManualLottoTickets(BettingInfo bettingInfo, ManualNumberBundle manualNumberBundle) {
        return new ManualLottoMachine(bettingInfo.getManualAmount(), manualNumberBundle.getManualNumbers())
                .buyTickets();
    }

}
