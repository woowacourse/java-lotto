package lotto.view;

import lotto.domain.*;

public class OutputView {
	public static void printPurchasedLottoTicketsCount(PurchaseMoney purchaseMoney) {
		System.out.println(purchaseMoney.countPurchasedTickets() + "개를 구매했습니다.");
	}

	public static void printPurchasedLottoTickets(PurchasedLottoTickets purchasedLottoTickets) {
		purchasedLottoTickets.getPurchasedLottoTickets()
				.forEach(System.out::println);
	}

	public static void printLottoResult(LottoResult lottoResult) {
		System.out.println("당첨통계");
		System.out.println("----------");
		for (WinningType winningType : lottoResult.getLottoResult().keySet()) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(winningType.getSameNumberCount())
					.append("개 일치");
			if (winningType.isBonusTRUE()) {
				stringBuilder.append(", 보너스 볼 일치");
			}
			stringBuilder.append("(")
					.append(winningType.getWinningAmount())
					.append("원)- ")
					.append(lottoResult.getLottoResult().get(winningType))
					.append("개");
		}
	}
}
