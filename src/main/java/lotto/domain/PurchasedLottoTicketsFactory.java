package lotto.domain;

import lotto.domain.LottoTicketFactory.LottoTicketFactory;

import java.util.ArrayList;
import java.util.List;

public class PurchasedLottoTicketsFactory {
	public static PurchasedLottoTickets create(PurchaseMoney purchaseMoney,
											   LottoTicketFactory LottoTicketFactory) {
		List<SerialLottoNumber> purchasedLottoTickets = new ArrayList<>();

		int count = purchaseMoney.countPurchasedTickets();
		for (int i = 0; i < count; i++) {
			purchasedLottoTickets.add(LottoTicketFactory.createLottoTicket());
		}

		return new PurchasedLottoTickets(purchasedLottoTickets);
	}
}
