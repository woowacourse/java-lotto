package lotto.domain.ticket;

import java.util.List;

import lotto.domain.ball.Ball;
import lotto.domain.rank.Rank;
import lotto.domain.ticket.validation.WinningTicketValidator;

public class WinningTicket {

    private final Ticket ticket;
    private final Ball bonusBall;

    public WinningTicket(final Ticket ticket, final Ball bonusBall) {
        WinningTicketValidator.validateWinningTicket(ticket, bonusBall);
        this.ticket = ticket;
        this.bonusBall = bonusBall;
    }

    public List<Rank> calculateRanks(final Tickets tickets) {
        return tickets.calculateRanks(this.ticket, this.bonusBall);
    }

}
