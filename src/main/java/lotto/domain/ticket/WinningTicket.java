package lotto.domain.ticket;

public interface WinningTicket {

    public int match(Ticket ticket);

    public boolean bonus(Ticket ticket);
}
