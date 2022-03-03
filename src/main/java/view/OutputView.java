package view;

import domain.Lotto;
import domain.LottoTickets;
import domain.WinningStatus;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
	private static final String DELIMITER = ", ";
	private static final String WIN = "이득";
	private static final String DRAW = "본전";
	private static final String LOSE = "손실";
	private static final double BASE_RATIO = 1.0;

	public static void printLottoTickets(LottoTickets lottoTickets) {
		int autoSize = lottoTickets.getAutoLottoTicketsSize();
		int manualLottoSize = lottoTickets.getManualLottoTicketsSize();
		System.out.println("수동으로 " + manualLottoSize + "장, 자동으로 " + autoSize + "개를 구매했습니다.");
		for (Lotto lotto : lottoTickets.getLottoTickets()) {
			printLotto(lotto);
		}
	}

	private static void printLotto(Lotto lotto) {
		System.out.println(
			"[" + lotto.getLottoNumbers()
				.stream()
				.map(String::valueOf)
				.collect(Collectors.joining(DELIMITER)) + "]");
	}

	public static void printStatistics(List<Integer> resultCount) {
		System.out.println("\n당첨 통계");
		System.out.println("---------");
		List<WinningStatus> winningStatuses = WinningStatus.getValues();
		for (int index = 0; index < winningStatuses.size(); index++) {
			printEachResult(winningStatuses.get(index), resultCount.get(index));
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
		System.out.println(
			winningStatus.getNumberMatches() + "개 일치" + bonusDisplay + "(" + winningStatus.getProfit() + "원) - " + count
				+ "개");
	}

	public static void printProfitRatio(double ratio) {
		System.out.println("총 수익률은 " + ratio + "입니다.(기준이 1이기 때문에 결과적으로 " + generateRatioMessage(ratio) + "이라는 의미임)");
	}

	public static void printErrorMessage(String message) {
		System.out.println(message);
	}

	private static String generateRatioMessage(double ratio) {
		if (ratio > BASE_RATIO) {
			return WIN;
		}
		if (ratio == BASE_RATIO) {
			return DRAW;
		}
		return LOSE;
	}
}
