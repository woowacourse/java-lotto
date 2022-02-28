package lotto.dto;

import java.util.List;

import lotto.domain.ticket.Ticket;

public class TicketDto {

    private final List<Integer> ballNumbers;

    public TicketDto(List<Integer> ballNumbers) {
        this.ballNumbers = ballNumbers;
    }

    public static TicketDto toDto(Ticket ticket) {
        return new TicketDto(ticket.getBallNumbers());
    }

    public Ticket toTicket() {
        return new Ticket(ballNumbers);
    }

    public List<Integer> getBallNumbers() {
        return this.ballNumbers;
    }

}
