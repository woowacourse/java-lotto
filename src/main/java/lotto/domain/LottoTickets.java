package lotto.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoTickets {
    private static final int LOTTO_TICKET_PRICE = 1000;
    private static final int ZERO = 0;
    private static final String NOT_ENOUGH_PURCHASING_MONEY = "금액이 부족하여 로또 티켓을 구매할 수 없습니다.";

    private final List<LottoTicket> lottoTickets;

    private LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public static LottoTickets generateAutomatic(PurchasingPrice purchasingPrice, LottoNumberGenerator lottoNumberGenerator) {
        int purchasableTicketCounts = purchasingPrice.calculatePurchasableTicketCounts(LOTTO_TICKET_PRICE);
        validateTicketCounts(purchasableTicketCounts);
        List<LottoTicket> lottoTickets = Stream.generate(() -> LottoTicket.from(lottoNumberGenerator.generate()))
                .limit(purchasableTicketCounts)
                .collect(Collectors.toList());
        return new LottoTickets(lottoTickets);
    }

    private static void validateTicketCounts(int purchasableTicketCounts) {
        if (purchasableTicketCounts == ZERO) {
            throw new IllegalArgumentException(NOT_ENOUGH_PURCHASING_MONEY);
        }
    }

    public LottoResult checkResult(WinningLottoTicket winningLottoTicket) {
        Map<LottoRank, Long> statistics = lottoTickets.stream()
                .map(winningLottoTicket::compareNumbers)
                .collect(Collectors.groupingBy(lottoRank -> lottoRank, () -> new EnumMap<>(LottoRank.class),
                        Collectors.counting()));
        return new LottoResult(statistics);
    }

    public int getPurchasingPrice() {
        return lottoTickets.size() * LOTTO_TICKET_PRICE;
    }

    public int getTicketCounts() {
        return lottoTickets.size();
    }

    public List<LottoTicket> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }
}
