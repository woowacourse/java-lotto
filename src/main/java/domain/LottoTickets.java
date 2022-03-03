package domain;

import domain.strategy.NumberGenerateStrategy;
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

    public static LottoTickets of(List<Set<Integer>> selfTicketNumbers, LottoMoney autoPurchaseLottoMoney,
                                  NumberGenerateStrategy numberGenerateStrategy) {
        List<LottoTicket> lottoTickets = generateTicket(selfTicketNumbers);
        int autoPurchaseCount = autoPurchaseLottoMoney.getAmount() / LottoTicket.TICKET_PRICE;
        lottoTickets.addAll(autoGenerateTickets(numberGenerateStrategy, autoPurchaseCount));
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

    private static boolean isMultiplesOfTicketPrice(int purchaseMoney) {
        return purchaseMoney % LottoTicket.TICKET_PRICE != 0;
    }

    public List<LottoTicket> getTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }

    public int getSelfPurchaseCount() {
        return selfPurchaseCount;
    }
}
