package lotto.domain.core;

public interface Ticket {

    public TicketNumbers ticketNumbers();

    public boolean contains(TicketNumber number);
}
