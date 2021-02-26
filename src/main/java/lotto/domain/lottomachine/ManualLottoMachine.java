package lotto.domain.lottomachine;

import java.util.List;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTickets;

public class ManualLottoMachine {
    public LottoTickets createTickets(List<LottoTicket> manualLottoTickets) {
        return new LottoTickets(manualLottoTickets);
    }
}
