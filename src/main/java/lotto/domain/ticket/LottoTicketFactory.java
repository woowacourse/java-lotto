package lotto.domain.ticket;

import lotto.domain.machine.Purchase;
import lotto.domain.ticket.generator.AutomaticGenerator;
import lotto.domain.ticket.generator.ManualGenerator;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketFactory {
    public static LottoTickets of(Purchase purchase) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        lottoTickets.addAll(new AutomaticGenerator(purchase.getAutoTicketQuantity()).generateTickets());
        lottoTickets.addAll(new ManualGenerator(purchase.getManualTicketQuantity(), purchase.getMultipleManualNumbers()).generateTickets());
        return new LottoTickets(lottoTickets);
    }
}
