package domain;

import domain.strategy.NumberGenerateStrategy;
import java.util.ArrayList;
import java.util.Collections;
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

    public static LottoTickets of(LottoMoney autoPurchaseLottoMoney,
                                  NumberGenerateStrategy numberGenerateStrategy) {
        int autoPurchaseCount = autoPurchaseLottoMoney.getAmount() / LottoTicket.TICKET_PRICE;
        List<LottoTicket> lottoTickets = new ArrayList<>(
                autoGenerateTickets(numberGenerateStrategy, autoPurchaseCount));
        return new LottoTickets(lottoTickets, 0);
    }

    public static LottoTickets from(List<Set<Integer>> selfTicketNumbers) {
        List<LottoTicket> lottoTickets = generateTicket(selfTicketNumbers);
        return new LottoTickets(lottoTickets, selfTicketNumbers.size());
    }

    private static List<LottoTicket> generateTicket(List<Set<Integer>> selfTicketNumbers) {
        return selfTicketNumbers.stream()
                .map(LottoTicket::from)
                .collect(Collectors.toList());
    }

    private static List<LottoTicket> autoGenerateTickets(NumberGenerateStrategy numberGenerateStrategy,
                                                         int autoPurchaseCount) {
        return IntStream.rangeClosed(1, autoPurchaseCount)
                .mapToObj(index -> LottoTicket.from(numberGenerateStrategy.generateNumbers()))
                .collect(Collectors.toList());
    }

    public LottoTickets concat(LottoTickets other) {
        List<LottoTicket> concatTickets = new ArrayList<>(lottoTickets);
        concatTickets.addAll(other.getTickets());
        return new LottoTickets(concatTickets, selfPurchaseCount + other.getSelfPurchaseCount());
    }

    public List<LottoTicket> getTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }

    public int getSelfPurchaseCount() {
        return selfPurchaseCount;
    }
}
