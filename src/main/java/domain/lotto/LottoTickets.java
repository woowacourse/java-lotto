package domain.lotto;

import domain.rank.Rank;
import domain.rank.Ranks;
import domain.Transaction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTickets {

    private final List<LottoTicket> tickets;

    public LottoTickets() {
        tickets = new ArrayList<>();
    }

    public void generateManual(final List<List<Integer>> manualNumbers) {
        manualNumbers.stream()
                .map(LottoTicket::generateManual)
                .forEach(tickets::add);
    }

    public void generateAuto(final Transaction transaction) {
        while (transaction.buyAutoLotto()) {
            tickets.add(LottoTicket.generateRandom());
        }
    }

    public Ranks checkMatch(final LottoTicket winningTicket, final LottoNumber bonusNumber) {
        List<Rank> ranks = tickets.stream()
                .map(ticket -> ticket.checkRanking(winningTicket, bonusNumber))
                .collect(Collectors.toList());
        return new Ranks(ranks);
    }

    public List<LottoTicket> toList() {
        return Collections.unmodifiableList(tickets);
    }
}
