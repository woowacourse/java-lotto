package view;

import domain.Lotto;
import domain.LottoTickets;
import domain.WinningStatus;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
	public static void printLottoTickets(LottoTickets lottoTickets) {
		System.out.println(lottoTickets.getLottoTicketsSize() + "개를 구매했습니다.");
		for (Lotto lotto : lottoTickets.getLottoTickets()) {
			printLotto(lotto);
		}
	}

	private static void printLotto(Lotto lotto) {
		System.out.println(
			"[" + lotto.getNumbers().stream().map(String::valueOf).collect(Collectors.joining(", ")) + "]");
	}

	public static void printStatistics(List<Integer> resultCount) {
		System.out.println("\n당첨 통계");
		System.out.println("---------");
		WinningStatus[] winningStatuses = WinningStatus.values();
		for (int index = 0; index < 6; index++) {
			printEachResult(winningStatuses[index], resultCount.get(index));
		}
	}

	private static void printEachResult(WinningStatus winningStatus, int count) {
		String bonusDisplay = " ";
		if (winningStatus.getProfit() == 0) {
			return;
		}
		if (winningStatus.getHitBonus()) {
			bonusDisplay = ", 보너스 볼 일치";
		}
		System.out.println(winningStatus.getNumberMatches() + "개 일치" + bonusDisplay + "(" + winningStatus.getProfit() + "원) - " + count + "개");
	}

	public static void printProfitRatio(float ratio) {
		String resultStatusMessage = "손실";
		if (ratio > 1.0f) {
			resultStatusMessage = "이득";
		}
		if (ratio == 1.0f) {
			resultStatusMessage = "본전";
		}
		System.out.println("총 수익률은 " + ratio + "입니다.(기준이 1이기 때문에 결과적으로 " + resultStatusMessage + "이라는 의미임)");
	}

	public static void printErrorMessage(String message) {
		System.out.println(message);
	}
}
