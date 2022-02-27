package lotto.dto;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.ticket.Ticket;
import lotto.domain.ticket.Tickets;

public class TicketsDto {

    private final List<TicketDto> ticketDtos;

    public TicketsDto(List<Ticket> tickets) {
        this.ticketDtos = tickets.stream()
                .map(TicketDto::toDto)
                .collect(Collectors.toUnmodifiableList());
    }

    public static TicketsDto toDto(Tickets tickets) {
        return new TicketsDto(tickets.getTickets());
    }

    public List<TicketDto> getTicketDtos() {
        return List.copyOf(ticketDtos);
    }

    public int getSize() {
        return ticketDtos.size();
    }

}
