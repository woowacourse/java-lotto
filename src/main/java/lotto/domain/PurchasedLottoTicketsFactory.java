package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class PurchasedLottoTicketsFactory {
	public static PurchasedLottoTickets of(PurchaseMoney purchaseMoney,
										   RandomGenerator randomGenerator) {
		List<SerialLottoNumber> purchasedLottoTickets = new ArrayList<>();

		for (int i = 0; i < purchaseMoney.countPurchasedTickets(); i++) {
			purchasedLottoTickets.add(
					SerialLottoNumberFactory.createRandomLottoTicket(randomGenerator));
		}

		return new PurchasedLottoTickets(purchasedLottoTickets);
	}
}
