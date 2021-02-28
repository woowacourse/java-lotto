package lotto.ticket;

import lotto.ranking.Ranking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tickets {
    private static final int SECOND_MATCH_COUNT = 5;

    private final List<Ticket> tickets;

    public Tickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public static Tickets joinTicket(Tickets manualTickets, Tickets autoTickets) {
        List<Ticket> tickets = new ArrayList<>();
        tickets.addAll(manualTickets.tickets);
        tickets.addAll(autoTickets.tickets);
        return new Tickets(tickets);
    }

    public List<Ranking> makeResult(WinnerTicket winnerTicket, BonusBall bonusBall) {
        List<Ranking> result = new ArrayList<>();
        for (Ticket ticket : tickets) {
            int matchCount = winnerTicket.findMatchCount(ticket);
            boolean bonus = isBonus(bonusBall, ticket, matchCount);
            result.add(Ranking.makePrice(matchCount, bonus));
        }
        return result;
    }

    private boolean isBonus(BonusBall bonusBall, Ticket ticket, int matchCount) {
        boolean bonus = false;
        if (matchCount == SECOND_MATCH_COUNT) {
            bonus = ticket.hasContainBonus(bonusBall);
        }
        return bonus;
    }

    public List<Ticket> getTickets() {
        return Collections.unmodifiableList(this.tickets);
    }
}
