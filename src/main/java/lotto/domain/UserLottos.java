package lotto.domain;

import lotto.service.LottoResult;

import java.util.List;


public class UserLottos {
    private final List<Ticket> tickets;

    public UserLottos(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public LottoResult result(WinningLotto winningTicket) {
        LottoResult results = new LottoResult();
        for (Ticket ticket : tickets) {
            results.plus(Rank.rank(winningTicket.match(ticket), winningTicket.bonus(ticket)));
        }
        return results;
    }

    public List<Ticket> tickets() {
        return tickets;
    }
}
