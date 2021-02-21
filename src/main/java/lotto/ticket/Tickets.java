package lotto.ticket;

import lotto.game.LottoCount;
import lotto.ticket.strategy.NumbersGenerator;
import lotto.ranking.Ranking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tickets {
    private static final int SECOND_MATCH_COUNT = 5;

    private final List<Ticket> tickets;

    public Tickets(LottoCount lottoCount, NumbersGenerator numbersGenerator) {
        this.tickets = new ArrayList<>();
        while (lottoCount.isGreaterThanZero()) {
            lottoCount = lottoCount.decreaseOne();
            tickets.add(new Ticket(numbersGenerator.generate()));
        }
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
