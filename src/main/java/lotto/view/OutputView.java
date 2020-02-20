package lotto.view;

import lotto.domain.PurchaseMoney;
import lotto.domain.PurchasedLottoTickets;
import lotto.domain.SerialLottoNumber;

public class OutputView {
	public static void printPurchasedLottoTicketsCount(PurchaseMoney purchaseMoney) {
		System.out.println(purchaseMoney.countPurchasedTickets() + "개를 구매했습니다.");
	}

	public static void printPurchasedLottoTickets(PurchasedLottoTickets purchasedLottoTickets) {
		purchasedLottoTickets.getPurchasedLottoTickets()
				.forEach(System.out::println);
	}

}
