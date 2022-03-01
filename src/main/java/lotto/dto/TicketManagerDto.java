package lotto.dto;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.ticket.Ticket;
import lotto.domain.ticket.TicketManager;

public class TicketManagerDto {

    private final List<TicketDto> preparedTicketDtos;
    private final List<TicketDto> generatedTicketDtos;

    public TicketManagerDto(final List<Ticket> preparedTickets, final List<Ticket> generatedTickets) {
        this.preparedTicketDtos = convertTicketsToDto(preparedTickets);
        this.generatedTicketDtos = convertTicketsToDto(generatedTickets);
    }

    private List<TicketDto> convertTicketsToDto(final List<Ticket> tickets) {
        return tickets.stream()
                .map(TicketDto::toDto)
                .collect(Collectors.toUnmodifiableList());
    }

    public static TicketManagerDto toDto(final TicketManager ticketManager) {
        return new TicketManagerDto(ticketManager.getPreparedTickets(), ticketManager.getGeneratedTickets());
    }

    public List<TicketDto> getPreparedTicketDtos() {
        return preparedTicketDtos;
    }

    public List<TicketDto> getGeneratedTicketDtos() {
        return generatedTicketDtos;
    }

}
