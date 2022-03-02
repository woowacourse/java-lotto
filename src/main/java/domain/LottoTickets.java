package domain;

import java.util.ArrayList;
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

    public LottoTickets(List<Set<Integer>> selfTicketNumbers, Money autoPurchaseMoney,
                        GenerateStrategy generateStrategy) {
        validatePurchaseMoney(autoPurchaseMoney);
        List<LottoTicket> lottoTickets = generateTicket(selfTicketNumbers);
        int autoPurchaseCount = autoPurchaseMoney.getAmount() / LottoTicket.TICKET_PRICE;
        lottoTickets.addAll(autoGenerateTickets(generateStrategy, autoPurchaseCount));
        this.lottoTickets = lottoTickets;
        this.selfPurchaseCount = selfTicketNumbers.size();
    }

    public List<LottoTicket> getTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }

    public int getSelfPurchaseCount() {
        return selfPurchaseCount;
    }

    private List<LottoTicket> generateTicket(List<Set<Integer>> selfTicketNumbers) {
        return selfTicketNumbers.stream()
                .map(LottoTicket::new)
                .collect(Collectors.toList());
    }

    private List<LottoTicket> autoGenerateTickets(GenerateStrategy generateStrategy, int autoPurchaseCount) {
        return IntStream.rangeClosed(1, autoPurchaseCount)
                .mapToObj(index -> new LottoTicket(generateStrategy))
                .collect(Collectors.toList());
    }

    private void validatePurchaseMoney(Money purchaseMoney) {
        if (isMultiplesOfTicketPrice(purchaseMoney.getAmount())) {
            throw new IllegalArgumentException(NOT_MULTIPLES_OF_PRICE_ERROR_MESSAGE);
        }
    }

    private boolean isMultiplesOfTicketPrice(int purchaseMoney) {
        return purchaseMoney % LottoTicket.TICKET_PRICE != 0;
    }
}
