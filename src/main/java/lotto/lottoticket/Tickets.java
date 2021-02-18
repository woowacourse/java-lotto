package lotto.lottoticket;

import lotto.lottogame.LottoCount;
import lotto.lottoticket.ticketnumber.NumbersGenerator;
import lotto.ranking.Ranking;

import java.util.ArrayList;
import java.util.List;

public class Tickets {
    private final List<Ticket> tickets;

    public Tickets(LottoCount lottoCount, NumbersGenerator numbersGenerator) {
        this.tickets = new ArrayList<>();
        while (lottoCount.isGreaterThanZero()) {
            lottoCount = lottoCount.decreaseOne();
            tickets.add(new Ticket(numbersGenerator));
        }
    }

    public List<Ranking> makeResult(WinnerTicket winnerTicket, BonusBall bonusBall) {
        List<Ranking> result = new ArrayList<>();
        for (Ticket ticket : tickets) {
            int matchCount = winnerTicket.findMatchCount(ticket);
            boolean bonus = ticket.hasContainBonus(bonusBall);
            result.add(Ranking.makePrice(matchCount, bonus));
        }
        return result;
    }

    public List<Ticket> getTickets() {
        return this.tickets;
    }
}
