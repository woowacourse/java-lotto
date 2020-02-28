package lotto.domain;

import java.util.List;

public class LottoTicketFactory {
    private static List<LottoTicket> createLottoTickets(PurchasingAmount purchasingAmount,
                                                        CreateLottoTicketStrategy createLottoTicketStrategy,
                                                        List<LottoTicket> lottoTickets) {

        return createLottoTicketStrategy.buyLottoTickets(purchasingAmount, lottoTickets);
    }
    public static void buyLottoTickets(PurchasingAmount purchasingAmount, List<LottoTicket> lottoTickets) {
        LottoTicketFactory.createLottoTickets(purchasingAmount, new CreateManualLottoTicket(), lottoTickets);
        LottoTicketFactory.createLottoTickets(purchasingAmount, new CreateRandomLottoTicket(), lottoTickets);
    }
}
