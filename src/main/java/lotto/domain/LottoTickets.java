package lotto.domain;

import java.util.List;
import java.util.Objects;

public class LottoTickets {
    private final List<LottoTicket> tickets;

    public LottoTickets(List<LottoTicket> tickets) {
        this.tickets = tickets;
    }

    public int size() {
        return tickets.size();
    }

    public LottoTicket getTicket(int index) {
        return tickets.get(index);
    }

    public LottoResult makeResultWith(LottoTicket winningLotto) {
        LottoResult lottoResult = new LottoResult();
        for (LottoTicket ticket : tickets) {
            Rank rank = calculateRankWith(ticket, winningLotto);
            lottoResult.increaseOneCountBy(rank);
        }
        return lottoResult;
    }

    private Rank calculateRankWith(LottoTicket lottoTicket, LottoTicket winningLotto) {
        int numOfMatching = lottoTicket.match(winningLotto);
        return Rank.valueOf(numOfMatching);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final LottoTickets that = (LottoTickets) o;
        return Objects.equals(tickets, that.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tickets);
    }
}
