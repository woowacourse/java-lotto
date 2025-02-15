package domain.dto;

import domain.Ticket;

public class TicketViewDto {

    private final int ticket;

    private TicketViewDto(int ticket) {
        this.ticket = ticket;
    }

    public static TicketViewDto from(Ticket ticket) {
        return new TicketViewDto(ticket.getQuantity());
    }

    public int getTicket() {
        return ticket;
    }
}
