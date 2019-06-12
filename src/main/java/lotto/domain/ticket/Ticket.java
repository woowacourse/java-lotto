package lotto.domain.ticket;

public interface Ticket {

    public TicketNumbers lottoNumbers();

    public boolean contains(TicketNumber number);
}
