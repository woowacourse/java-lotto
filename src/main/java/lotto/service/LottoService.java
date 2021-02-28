package lotto.service;

import java.util.List;
import lotto.domain.PurchaseInfo;
import lotto.domain.lottomachine.AutoLottoMachine;
import lotto.domain.lottomachine.ManualLottoMachine;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTickets;

public class LottoService {

    public LottoTickets buyTicket(PurchaseInfo purchaseInfo, List<LottoTicket> manualLottoTickets) {
        AutoLottoMachine autoLottoMachine = new AutoLottoMachine();
        ManualLottoMachine manualLottoMachine = new ManualLottoMachine();

        LottoTickets autoTicket = autoLottoMachine
            .createTickets(purchaseInfo.getPurchaseAutoCount());
        LottoTickets manualTicket = manualLottoMachine.createTickets(manualLottoTickets);
        return joinTickets(autoTicket, manualTicket);
    }

    private LottoTickets joinTickets(LottoTickets autoTicket, LottoTickets manualTicket) {
        return new LottoTickets(autoTicket.getLottoTickets(), manualTicket.getLottoTickets());
    }
}
