package lotto.dto;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.ticket.Ticket;

public class TicketDto {

    private final List<Integer> ballNumbers;

    public TicketDto(final List<Integer> ballNumbers) {
        this.ballNumbers = new ArrayList<>(ballNumbers);
    }

    public static TicketDto toDto(Ticket ticket) {
        return new TicketDto(ticket.getBallNumbers());
    }

    public Ticket toTicket() {
        return new Ticket(ballNumbers);
    }

    public List<Integer> getBallNumbers() {
        return List.copyOf(ballNumbers);
    }

}
