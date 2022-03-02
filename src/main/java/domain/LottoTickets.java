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
    public static final String NOT_MULTIPLES_OF_PRICE_ERROR_MESSAGE
            = String.format("금액을 %d의 배수로 입력해주세요.", LottoTicket.TICKET_PRICE);

    private LottoTickets(List<LottoTicket> lottoTickets, int selfPurchaseCount) {
        this.lottoTickets = lottoTickets;
        this.selfPurchaseCount = selfPurchaseCount;
    }

    public static LottoTickets from(List<Set<Integer>> selfTicketNumbers, Money autoPurchaseMoney,
                                    NumberGenerateStrategy numberGenerateStrategy) {
        validatePurchaseMoney(autoPurchaseMoney);
        List<LottoTicket> lottoTickets = generateTicket(selfTicketNumbers);
        int autoPurchaseCount = autoPurchaseMoney.getAmount() / LottoTicket.TICKET_PRICE;
        lottoTickets.addAll(autoGenerateTickets(numberGenerateStrategy, autoPurchaseCount));
        return new LottoTickets(lottoTickets, selfTicketNumbers.size());
    }

    private static List<LottoTicket> generateTicket(List<Set<Integer>> selfTicketNumbers) {
        return selfTicketNumbers.stream()
                .map(LottoTicket::of)
                .collect(Collectors.toList());
    }

    private static List<LottoTicket> autoGenerateTickets(NumberGenerateStrategy numberGenerateStrategy,
                                                         int autoPurchaseCount) {
        return IntStream.rangeClosed(1, autoPurchaseCount)
                .mapToObj(index -> LottoTicket.of(numberGenerateStrategy.generateNumbers()))
                .collect(Collectors.toList());
    }

    private static void validatePurchaseMoney(Money purchaseMoney) {
        if (isMultiplesOfTicketPrice(purchaseMoney.getAmount())) {
            throw new IllegalArgumentException(NOT_MULTIPLES_OF_PRICE_ERROR_MESSAGE);
        }
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
