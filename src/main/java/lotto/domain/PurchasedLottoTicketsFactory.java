package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class PurchasedLottoTicketsFactory {
	public static PurchasedLottoTickets create(PurchaseMoney purchaseMoney) {
		List<SerialLottoNumber> purchasedLottoTickets = new ArrayList<>();
		RandomLottoNumbersGenerator randomLottoNumbersGenerator = new RandomLottoNumbersGenerator();

		for (int i = 0; i < purchaseMoney.countPurchasedTickets(); i++) {
			purchasedLottoTickets.add(
					SerialLottoNumberFactory.createRandomLottoTicket(randomLottoNumbersGenerator));
		}

		return new PurchasedLottoTickets(purchasedLottoTickets);
	}
}
