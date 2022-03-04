package lotto.model.ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.model.money.Money;
import lotto.model.result.LottoRanks;
import lotto.model.ticket.number.LottoNumber;
import lotto.model.utils.NumberGenerator;

public class LottoTickets {

    private final List<LottoTicket> tickets;

    public LottoTickets(List<LottoTicket> tickets) {
        this.tickets = new ArrayList<>(tickets);
    }

    public LottoTickets buyAutoTickets(NumberGenerator generator, Money money) {
        this.tickets.addAll(IntStream.range(0, money.countBuyable())
                .mapToObj(x -> LottoTicket.createSortedTicket(generator))
                .collect(Collectors.toList()));
        return this;
    }

    public static LottoTickets buyManualTickets(List<List<Integer>> numberTickets) {
        return new LottoTickets(numberTickets.stream().
                map(numberTicket -> new LottoTicket(numberTicket.stream()
                        .map(LottoNumber::from)
                        .collect(Collectors.toList())))
                .collect(Collectors.toList()));
    }

    public LottoRanks compareResult(WinningTicket winningTicket) {
        return new LottoRanks(tickets.stream()
                .map(winningTicket::judgeRank)
                .collect(Collectors.toList()));
    }

    public List<LottoTicket> getTickets() {
        return Collections.unmodifiableList(tickets);
    }

    public int size() {
        return tickets.size();
    }
}
