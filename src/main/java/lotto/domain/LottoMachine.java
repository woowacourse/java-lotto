package lotto.domain;

public class LottoMachine {
    private static final String NOT_ENOUGH_MONEY = "금액이 부족하여 로또 티켓을 구매할 수 없습니다.";
    private static final int LOTTO_TICKET_COST = 1000;
    private static final int ZERO = 0;

    private final LottoNumberGenerator lottoNumberGenerator;

    public LottoMachine(LottoNumberGenerator lottoNumberGenerator) {
        this.lottoNumberGenerator = lottoNumberGenerator;
    }

    public LottoTickets issueLottoTickets(PurchasingPrice purchasingPrice, ManualTickets manualTickets) {
        int purchasableTicketCounts = purchasingPrice.calculatePurchasableTicketCounts(LOTTO_TICKET_COST);
        int manualTicketCounts = manualTickets.getManualTickets().size();
        validatePurchasingPrice(purchasableTicketCounts, manualTicketCounts);
        int automaticTicketCounts = purchasableTicketCounts - manualTicketCounts;
        return LottoTickets.generate(manualTickets, automaticTicketCounts, lottoNumberGenerator);
    }

    private void validatePurchasingPrice(int purchasableTicketCounts, int manualTicketCounts) {
        if (purchasableTicketCounts == ZERO || manualTicketCounts > purchasableTicketCounts) {
            throw new IllegalArgumentException(NOT_ENOUGH_MONEY);
        }
    }

    public int calculatePurchasingPrice(LottoTickets lottoTickets) {
        return lottoTickets.getTicketCounts() * LOTTO_TICKET_COST;
    }
}
