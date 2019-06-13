package lotto.domain.core;

import lotto.domain.LottoResult;

import java.util.List;


public class UserLottos implements UserTickets {
    private final List<Ticket> tickets;

    public UserLottos(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public LottoResult result(WinningTicket winningTicket) {
        LottoResult results = new LottoResult();
        for (Ticket ticket : tickets) {
            results.plus(Rank.rank(winningTicket.match(ticket), winningTicket.bonus(ticket)));
        }
        return results;
    }

    @Override
    public List<Ticket> tickets() {
        return tickets;
    }
}
