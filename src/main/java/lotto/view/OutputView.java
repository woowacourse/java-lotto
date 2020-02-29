package lotto.view;

import lotto.domain.result.WinningResult;
import lotto.domain.ticket.LottoTickets;
import lotto.domain.ticket.PurchaseLottoCount;
import lotto.util.PrintTextUtil;

public class OutputView {
	private static final String LINE_SEPARATOR = System.lineSeparator();
	private static final String STATISTICS_MESSAGE = String.format("%s당첨 통계%s---------", LINE_SEPARATOR, LINE_SEPARATOR);
	private static final String LOTTO_COUNT_MESSAGE = String.format("수동 %%s개, 자동 %%s개를 구매했습니다.%s", LINE_SEPARATOR);
	private static final String TOTAL_PROFIT_MESSAGE = "총 수익률은 %d%% 입니다.";

	private OutputView() {
	}

	public static void printPurchaseLottoInfo(PurchaseLottoCount purchaseCount) {
		System.out.printf(LOTTO_COUNT_MESSAGE, purchaseCount.getManualCount(), purchaseCount.calculateAutoCount());
	}

	public static void printPurchaseLottoTickets(LottoTickets lottoTickets) {
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
