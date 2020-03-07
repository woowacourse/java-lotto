package lotto.domain.strategy;

import lotto.domain.lottoTicket.LottoTickets;
import lotto.domain.purchase.PurchasingCount;

public interface LottoTicketsGenerator {

	LottoTickets generate(PurchasingCount purchasingCount);

}
