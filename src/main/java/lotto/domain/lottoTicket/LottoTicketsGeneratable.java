package lotto.domain.lottoTicket;

import lotto.domain.purchase.PurchasingCount;

public interface LottoTicketsGeneratable {
	LottoTickets generate(PurchasingCount purchasingCount);
}
