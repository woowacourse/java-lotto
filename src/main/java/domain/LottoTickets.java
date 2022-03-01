package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoTickets {
    private final List<LottoTicket> lottoTickets;
    public static final String NOT_MULTIPLES_OF_PRICE_ERROR_MESSAGE
            = String.format("금액을 %d의 배수로 입력해주세요.", LottoTicket.TICKET_PRICE);

    public LottoTickets(Money purchaseMoney, GenerateStrategy generateStrategy) {
        validatePurchaseMoney(purchaseMoney);
        int purchaseCount = purchaseMoney.getAmount() / LottoTicket.TICKET_PRICE;
        lottoTickets = IntStream
                .rangeClosed(1, purchaseCount)
                .mapToObj(index -> new LottoTicket(generateStrategy))
                .collect(Collectors.toList());
    }

    public List<LottoTicket> getTickets() {
        return Collections.unmodifiableList(lottoTickets);
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
