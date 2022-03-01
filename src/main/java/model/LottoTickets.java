package model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoTickets {
    private static final int TICKET_PRICE = 1000;

    private final List<LottoTicket> lottoTickets;

    public LottoTickets(int purchaseMoney, GenerateStrategy generateStrategy) {
        int purchaseCount = purchaseMoney / TICKET_PRICE;
        lottoTickets = IntStream
                .rangeClosed(1, purchaseCount)
                .mapToObj(index -> new LottoTicket(generateStrategy))
                .collect(Collectors.toList());
    }

    public List<LottoTicket> getTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }

    public int getSize() {
        return lottoTickets.size();
    }
}
