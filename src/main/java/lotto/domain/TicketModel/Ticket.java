package lotto.domain.TicketModel;

public interface Ticket {

    public TicketNumbers lottoNumbers();

    public boolean contains(TicketNumber number);
}
