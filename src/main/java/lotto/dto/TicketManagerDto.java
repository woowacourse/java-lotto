package lotto.dto;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.ticket.Ticket;
import lotto.domain.ticket.TicketManager;

public class TicketManagerDto {

    private final List<TicketDto> manualTickets;
    private final List<TicketDto> automaticTickets;

    public TicketManagerDto(final List<Ticket> manualTickets, final List<Ticket> automaticTickets) {
        this.manualTickets = convertTicketsToDto(manualTickets);
        this.automaticTickets = convertTicketsToDto(automaticTickets);
    }

    private List<TicketDto> convertTicketsToDto(final List<Ticket> tickets) {
        return tickets.stream()
                .map(TicketDto::toDto)
                .collect(Collectors.toUnmodifiableList());
    }

    public static TicketManagerDto toDto(final TicketManager ticketManager) {
        return new TicketManagerDto(ticketManager.getManualTickets(), ticketManager.getAutomaticTickets());
    }

    public List<TicketDto> getManualTickets() {
        return manualTickets;
    }

    public List<TicketDto> getAutomaticTickets() {
        return automaticTickets;
    }

}
