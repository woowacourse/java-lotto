package lotto.domain.core;

public class WinningLotto implements WinningTicket {
    private final Ticket winningTicket;
    private final TicketNumber bonus;

    public WinningLotto(Ticket winningTicket, TicketNumber bonus) {
        this.winningTicket = winningTicket;
        this.bonus = bonus;
    }

    @Override
    public int match(Ticket lotto) {
        return (int) lotto.ticketNumbers().numbers().stream()
                .filter(ticketNumber -> winningTicket.contains(ticketNumber))
                .count();
    }

    @Override
    public boolean bonus(Ticket lotto) {
        return lotto.ticketNumbers().contains(bonus);
    }
}
