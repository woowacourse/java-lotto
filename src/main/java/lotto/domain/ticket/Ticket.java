package lotto.domain.ticket;

public interface Ticket {

    public TicketNumbers ticketNumbers();

    public boolean contains(TicketNumber number);
}
