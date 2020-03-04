package lotto.model;

import java.util.List;

public class ManualTicket implements LottoGenerator {
    @Override
    public List<LottoTicket> generate(TicketInformation ticketInformation) {
        return ticketInformation.getManualTickets();
    }
}
