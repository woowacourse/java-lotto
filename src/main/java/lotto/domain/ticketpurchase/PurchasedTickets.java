package lotto.domain.ticketpurchase;

import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PurchasedTickets {
    private final LottoTickets manualTickets;
    private final LottoTickets autoTickets;

    public PurchasedTickets(LottoTickets manualTickets, LottoTickets autoTickets) {
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
