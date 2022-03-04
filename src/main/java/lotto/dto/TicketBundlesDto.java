package lotto.dto;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.ticket.Ticket;
import lotto.domain.ticket.TicketBundles;

public class TicketBundlesDto {

    private final List<TicketDto> manualTickets;
    private final List<TicketDto> automaticTickets;

    public TicketBundlesDto(final List<Ticket> manualTickets, final List<Ticket> automaticTickets) {
        this.manualTickets = convertTicketsToDto(manualTickets);
        this.automaticTickets = convertTicketsToDto(automaticTickets);
    }

    private List<TicketDto> convertTicketsToDto(final List<Ticket> tickets) {
        return tickets.stream()
                .map(TicketDto::toDto)
                .collect(Collectors.toUnmodifiableList());
    }

    public static TicketBundlesDto toDto(final TicketBundles ticketBundles) {
        return new TicketBundlesDto(ticketBundles.getManualTickets(), ticketBundles.getAutomaticTickets());
    }

    public List<TicketDto> getManualTickets() {
        return manualTickets;
    }

    public List<TicketDto> getAutomaticTickets() {
        return automaticTickets;
    }

}
