package lotto.model.ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.model.money.Money;
import lotto.model.result.LottoRank;
import lotto.model.result.LottoRanks;
import lotto.model.utils.NumberGenerator;

public class LottoTickets {

    private final List<LottoTicket> tickets;

    public LottoTickets(List<LottoTicket> tickets) {
        this.tickets = new ArrayList<>(tickets);
    }

    public static LottoTickets buy(NumberGenerator generator, Money money) {
        List<LottoTicket> tickets = IntStream.range(0, money.count())
            .mapToObj(x -> LottoTicket.createSortedTicket(generator))
            .collect(Collectors.toList());
        return new LottoTickets(tickets);
    }

    public LottoRanks compareResult(WinningTicket winningTicket) {
        List<LottoRank> lottoRanks = tickets.stream()
                .map(winningTicket::judgeRank)
                .collect(Collectors.toList());
        return new LottoRanks(lottoRanks);
    }

    public List<LottoTicket> getTickets() {
        return Collections.unmodifiableList(tickets);
    }

    public int size() {
        return tickets.size();
    }
}
