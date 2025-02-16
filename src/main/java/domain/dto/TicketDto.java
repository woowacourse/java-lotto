package domain.dto;

import domain.Ticket;

public class TicketDto {

    private final int ticket;

    private TicketDto(int ticket) {
        this.ticket = ticket;
    }

    public static TicketDto from(Ticket ticket) {
        return new TicketDto(ticket.getQuantity());
    }

    public int getTicket() {
        return ticket;
    }
}
