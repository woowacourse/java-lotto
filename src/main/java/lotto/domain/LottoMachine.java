package lotto.domain;

import lotto.dto.TicketNumbersDto;

import java.util.List;

public class LottoMachine {
    private static final String NOT_ENOUGH_MONEY = "금액이 부족하여 로또 티켓을 구매할 수 없습니다.";
    private static final int LOTTO_TICKET_COST = 1000;
    private static final int ZERO = 0;

    private final LottoNumberGenerator lottoNumberGenerator;

    public LottoMachine(LottoNumberGenerator lottoNumberGenerator) {
        this.lottoNumberGenerator = lottoNumberGenerator;
    }

    public LottoTickets issueLottoTickets(PurchasingPrice purchasingPrice, List<TicketNumbersDto> manualTicketsNumbers) {
        int purchasableTicketCounts = purchasingPrice.calculatePurchasableTicketCounts(LOTTO_TICKET_COST);
        int manualTicketCounts = manualTicketsNumbers.size();
        validatePurchasingPrice(purchasableTicketCounts, manualTicketCounts);
        int automaticTicketCounts = purchasableTicketCounts - manualTicketCounts;
        LottoTickets manualLottoTickets = LottoTickets.generateManual(manualTicketsNumbers);
        LottoTickets automaticLottoTickets = LottoTickets.generateAutomatic(automaticTicketCounts, lottoNumberGenerator);
        return manualLottoTickets.concat(automaticLottoTickets);
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
