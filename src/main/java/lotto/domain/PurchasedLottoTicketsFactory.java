package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class PurchasedLottoTicketsFactory {
	public static PurchasedLottoTickets create(PurchaseMoney purchaseMoney) {
		List<SerialLottoNumber> purchasedLottoTickets = new ArrayList<>();
		RandomLottoTicketFactory randomLottoTicketFactory = new RandomLottoTicketFactory();

		int count = purchaseMoney.countPurchasedTickets();
		for (int i = 0; i < count; i++) {
			purchasedLottoTickets.add(randomLottoTicketFactory.createLottoTicket());
		}

		return new PurchasedLottoTickets(purchasedLottoTickets);
	}
}
