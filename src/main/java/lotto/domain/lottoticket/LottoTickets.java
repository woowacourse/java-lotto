package lotto.domain.lottoticket;

import lotto.domain.Rank;
import lotto.domain.lottonumber.LottoNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LottoTickets {
    private final List<LottoTicket> tickets;

    public LottoTickets() {
        this.tickets = new ArrayList<>();
    }

    public LottoTickets(List<LottoTicket> tickets) {
        this.tickets = new ArrayList<>(tickets);
    }

    public static LottoTickets join(LottoTickets lastLottoTickets, LottoTickets newLottoTickets) {
        return lastLottoTickets.join(newLottoTickets);
    }

    private LottoTickets join(LottoTickets lottoTickets) {
        return lottoTickets.join(this.tickets);
    }

    private LottoTickets join(List<LottoTicket> tickets) {
        List<LottoTicket> ticketsToReturn = new ArrayList<>();
        ticketsToReturn.addAll(this.tickets);
        ticketsToReturn.addAll(tickets);
        return new LottoTickets(ticketsToReturn);
    }

    public void add(LottoTicket ticket) {
        tickets.add(ticket);
    }

    public int size() {
        return tickets.size();
    }

    public LottoTicket getTicket(int index) {
        return tickets.get(index);
    }

    public Map<Rank, Integer> countRanksWith(LottoTicket winningLotto, LottoNumber bonusBall) {
        Map<Rank, Integer> rankCounts = Rank.getInitializedCounts();
        for (LottoTicket ticket : tickets) {
            Rank rank = ticket.match(winningLotto, bonusBall);
            rankCounts.put(rank, rankCounts.get(rank) + 1);
        }
        return rankCounts;
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
