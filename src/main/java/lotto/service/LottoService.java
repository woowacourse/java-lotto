package lotto.service;

import lotto.domain.PurchaseInfo;
import lotto.domain.lottomachine.AutoLottoMachine;
import lotto.domain.lottomachine.ManualLottoMachine;
import lotto.domain.ticket.LottoTickets;

public class LottoService {

    public LottoTickets buyTicket(PurchaseInfo purchaseInfo) {
        AutoLottoMachine autoLottoMachine = new AutoLottoMachine();
        ManualLottoMachine manualLottoMachine = new ManualLottoMachine();

        LottoTickets autoTicket = autoLottoMachine
            .createTickets(purchaseInfo.getPurchaseAutoCount());
        LottoTickets manualTicket = manualLottoMachine
            .createTickets(purchaseInfo.getLottoTickets());
        return joinTickets(autoTicket, manualTicket);
    }

    private LottoTickets joinTickets(LottoTickets autoTicket, LottoTickets manualTicket) {
        return new LottoTickets(autoTicket, manualTicket);
    }
}
