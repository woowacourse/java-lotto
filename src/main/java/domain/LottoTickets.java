package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoTickets {
    public static final int TICKET_PRICE = 1000;
    public static final String NOT_POSITIVE_ERROR_MESSAGE = "금액은 양수로 입력해야 합니다.";
    public static final String NOT_MULTIPLES_OF_PRICE_ERROR_MESSAGE
            = String.format("금액을 %d의 배수로 입력해주세요.", TICKET_PRICE);

    private final List<LottoTicket> lottoTickets;

    public LottoTickets(int purchaseMoney, GenerateStrategy generateStrategy) {
        validatePurchaseMoney(purchaseMoney);
        int purchaseCount = purchaseMoney / TICKET_PRICE;
        lottoTickets = IntStream
                .rangeClosed(1, purchaseCount)
                .mapToObj(index -> new LottoTicket(generateStrategy))
                .collect(Collectors.toList());
    }

    public List<LottoTicket> getTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }

    private void validatePurchaseMoney(int purchaseMoney) {
        if (isMultiplesOfTicketPrice(purchaseMoney)) {
            throw new IllegalArgumentException(NOT_MULTIPLES_OF_PRICE_ERROR_MESSAGE);
        }

        if (isNotPositiveNumber(purchaseMoney)) {
            throw new IllegalArgumentException(NOT_POSITIVE_ERROR_MESSAGE);
        }
    }

    private boolean isNotPositiveNumber(int purchaseMoney) {
        return purchaseMoney <= 0;
    }

    private boolean isMultiplesOfTicketPrice(int purchaseMoney) {
        return purchaseMoney % TICKET_PRICE != 0;
    }
}
