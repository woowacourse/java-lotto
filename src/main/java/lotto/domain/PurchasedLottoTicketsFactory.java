package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class PurchasedLottoTicketsFactory {
	public static PurchasedLottoTickets of(PurchaseMoney purchaseMoney,
										   RandomLottoTicketFactory randomLottoTicketFactory) {
		List<SerialLottoNumber> purchasedLottoTickets = new ArrayList<>();

		for (int i = 0; i < purchaseMoney.countPurchasedTickets(); i++) {
			purchasedLottoTickets.add(
					randomLottoTicketFactory.create());
		}

		return new PurchasedLottoTickets(purchasedLottoTickets);
	}
}
