package domain;

import domain.strategy.NumberGenerateStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoTickets {
    private final List<LottoTicket> lottoTickets;
    private final int selfPurchaseCount;

    private LottoTickets(List<LottoTicket> lottoTickets, int selfPurchaseCount) {
        this.lottoTickets = lottoTickets;
        this.selfPurchaseCount = selfPurchaseCount;
    }

    public static LottoTickets generateAutoTickets(LottoMoney autoPurchaseLottoMoney,
                                                   NumberGenerateStrategy numberGenerateStrategy) {
        int autoPurchaseCount = autoPurchaseLottoMoney.getCanPurchaseTicketCount();
        List<LottoTicket> lottoTickets = new ArrayList<>(
                autoGenerateTickets(numberGenerateStrategy, autoPurchaseCount));
        return new LottoTickets(lottoTickets, 0);
    }

    public static LottoTickets fromTicketNumbers(List<Set<Integer>> ticketNumbers) {
        List<LottoTicket> lottoTickets = boxTicket(ticketNumbers);
        return new LottoTickets(lottoTickets, ticketNumbers.size());
    }

    private static List<LottoTicket> boxTicket(List<Set<Integer>> selfTicketNumbers) {
        return selfTicketNumbers.stream()
                .map(LottoTicket::fromNumberValues)
                .collect(Collectors.toList());
    }

    private static List<LottoTicket> autoGenerateTickets(NumberGenerateStrategy numberGenerateStrategy,
                                                         int autoPurchaseCount) {
        return IntStream.rangeClosed(1, autoPurchaseCount)
                .mapToObj(index -> LottoTicket.fromNumberValues(numberGenerateStrategy.generateNumbers()))
                .collect(Collectors.toList());
    }

    public LottoTickets concat(LottoTickets other) {
        List<LottoTicket> concatTickets = new ArrayList<>(lottoTickets);
        concatTickets.addAll(other.getTickets());
        return new LottoTickets(concatTickets, selfPurchaseCount + other.getSelfPurchaseCount());
    }

    public List<LottoTicket> getTickets() {
        return List.copyOf(lottoTickets);
    }

    public int getSelfPurchaseCount() {
        return selfPurchaseCount;
    }
}
