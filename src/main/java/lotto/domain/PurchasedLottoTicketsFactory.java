package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class PurchasedLottoTicketsFactory {
	public static PurchasedLottoTickets create(PurchaseMoney purchaseMoney) {
		List<SerialLottoNumber> purchasedLottoTickets = new ArrayList<>();

		int count = purchaseMoney.countPurchasedTickets();
		for (int i = 0; i < count; i++) {
			purchasedLottoTickets.add(RandomLottoTicketFactory.createLottoTicket());
		}

		return new PurchasedLottoTickets(purchasedLottoTickets);
	}
}
