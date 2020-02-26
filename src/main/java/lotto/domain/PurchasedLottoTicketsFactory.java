package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class PurchasedLottoTicketsFactory {
	public static PurchasedLottoTickets of(int autoTicketNumber,
										   RandomLottoTicketFactory randomLottoTicketFactory) {
		List<SerialLottoNumber> purchasedLottoTickets = new ArrayList<>();

		for (int i = 0; i < autoTicketNumber; i++) {
			purchasedLottoTickets.add(
					randomLottoTicketFactory.create());
		}

		return new PurchasedLottoTickets(purchasedLottoTickets);
	}
}
