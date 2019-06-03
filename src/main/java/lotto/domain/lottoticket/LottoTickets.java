package lotto.domain.lottoticket;

import lotto.domain.Rank;

import java.util.List;
import java.util.Map;
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

    public Map<Rank, Integer> countRanksWith(LottoTicket winningLotto) {
        Map<Rank, Integer> countedRanks = Rank.getInitializedCounts();
        for (LottoTicket ticket : tickets) {
            Rank rank = ticket.match(winningLotto);
            countedRanks.put(rank, countedRanks.get(rank) + 1);
        }
        return countedRanks;
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
