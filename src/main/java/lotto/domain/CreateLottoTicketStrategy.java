package lotto.domain;

import java.util.List;

public interface CreateLottoTicketStrategy {
    List<LottoTicket> buyLottoTickets(PurchasingAmount purchasingAmount, List<LottoTicket> lottoTickets);
}
