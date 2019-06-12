package lotto.domain;

import lotto.domain.ticket.Ticket;
import lotto.domain.ticket.WinningTicket;

import java.util.List;


public class UserLottos {
    private final List<Ticket> tickets;
    private LottoResult results;

    public UserLottos(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public LottoResult result(WinningTicket winningTicket) {
        this.results = new LottoResult();
        for (Ticket ticket : tickets) {
            results.plus(Rank.rank(winningTicket.match(ticket), winningTicket.bonus(ticket)));
        }
        return results;
    }

    public List<Ticket> tickets() {
        return tickets;
    }
}
