package lotto.view;

import lotto.domain.PurchaseMoney;

public class OutputView {
	public static void printCountPurchasedLottoTickets(PurchaseMoney purchaseMoney) {
		System.out.println(purchaseMoney.countPurchasedTickets() + "개를 구매했습니다.");
	}

}
