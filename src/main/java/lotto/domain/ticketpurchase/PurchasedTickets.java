package lotto.domain.ticketpurchase;

import lotto.domain.ticket.AutoTickets;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.ManualTickets;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PurchasedTickets {
    private final ManualTickets manualTickets;
    private final AutoTickets autoTickets;

    public PurchasedTickets(ManualTickets manualTickets, AutoTickets autoTickets) {
        this.manualTickets = manualTickets;
        this.autoTickets = autoTickets;
    }

    public List<LottoTicket> getTickets() {
        return Stream.of(manualTickets.getTickets(), autoTickets.getTickets())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public int getManualTicketCount() {
        return manualTickets.size();
    }

    public int getAutoTicketCount() {
        return autoTickets.size();
    }
}
