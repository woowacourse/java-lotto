package lotto.model.ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.model.money.Money;
import lotto.model.result.LottoRank;
import lotto.model.result.LottoRanks;
import lotto.model.ticket.number.LottoNumber;
import lotto.model.utils.NumberGenerator;

public class LottoTickets {

    private final List<LottoTicket> tickets;

    public LottoTickets(List<LottoTicket> tickets) {
        this.tickets = new ArrayList<>(tickets);
    }

    public void buyAutoTickets(NumberGenerator generator, Money money) {
        System.out.println(money.countBuyable());
        List<LottoTicket> tickets = IntStream.range(0, money.countBuyable())
            .mapToObj(x -> LottoTicket.createSortedTicket(generator))
            .collect(Collectors.toList());
        this.tickets.addAll(tickets);
    }

    public static LottoTickets buyManualTickets(List<List<Integer>> numberTickets, Money money) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (List<Integer> numberTicket : numberTickets) {
            LottoTicket lottoTicket = new LottoTicket(numberTicket.stream()
                    .map(LottoNumber::new)
                    .collect(Collectors.toList()));
            lottoTickets.add(lottoTicket);
            money.decreaseByUnit();
        }
        return new LottoTickets(lottoTickets);
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
