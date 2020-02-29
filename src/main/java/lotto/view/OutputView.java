package lotto.view;

import lotto.domain.result.WinningResult;
import lotto.domain.ticket.LottoTickets;
import lotto.domain.ticket.PurchaseLottoCount;
import lotto.util.PrintTextUtil;

public class OutputView {
	private static final String STATISTICS_MESSAGE = "\n당첨 통계\n---------";
	private static final String LOTTO_COUNT_MESSAGE = "수동 %s개, 자동 %s개를 구매했습니다.\n";
	private static final String TOTAL_PROFIT_MESSAGE = "총 수익률은 %d%% 입니다.";

	private OutputView() {
	}

	public static void printPurchaseLottos(PurchaseLottoCount purchaseCount, LottoTickets lottoTickets) {
		System.out.printf(LOTTO_COUNT_MESSAGE, purchaseCount.getManualCount(), purchaseCount.calculateAutoCount());
		System.out.println(PrintTextUtil.createLottosText(lottoTickets));
	}

	public static void printStatistics(WinningResult winningResult) {
		System.out.println(STATISTICS_MESSAGE);
		System.out.println(PrintTextUtil.createLottosStatisticText(winningResult));
		System.out.printf(TOTAL_PROFIT_MESSAGE, winningResult.calculateProfitRate());
	}

	public static void printExceptionMessage(String message) {
		System.out.println(message);
	}
}
