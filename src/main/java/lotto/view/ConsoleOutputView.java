package lotto.view;

import lotto.domain.lottoTicket.LottoTickets;
import lotto.domain.result.WinningResult;
import lotto.util.StringUtil;

public class ConsoleOutputView {
	private static final String PURCHASE_LOTTO_COMPLETE_MESSAGE = "%d개를 구매했습니다.";
	private static final String WINNING_RESULT_NOTICE_MESSAGE = "당첨 통계";
	private static final String DIVIDING_LINE = "---------";
	private static final String TOTAL_WINNING_RATE_MESSAGE = "총 수익률은 %d%%입니다.";

	private ConsoleOutputView() {
	}

	public static void printException(String exceptionMessage) {
		System.out.println(exceptionMessage);
	}

	public static void printNumberOfPurchasedLottoTicket(long numberOfPurchasedLotto) {
		System.out.println(String.format(PURCHASE_LOTTO_COMPLETE_MESSAGE, numberOfPurchasedLotto));
	}

	public static void printPurchasedLottoTickets(LottoTickets purchasedLottoTickets) {
		purchasedLottoTickets.getLottoTickets().stream()
			.map(StringUtil::joiningLottoNumbersAt)
			.forEach(System.out::println);
		System.out.println();
	}

	public static void printWinningResult(WinningResult winningResult) {
		System.out.println();
		System.out.println(WINNING_RESULT_NOTICE_MESSAGE);
		System.out.println(DIVIDING_LINE);
		printWinningLottoTicketByLottoRank(winningResult);
		printWinningRate(winningResult);

	}

	private static void printWinningLottoTicketByLottoRank(WinningResult winningResult) {
		winningResult.getWinningResult().forEach((lottoRank, lottoRankCount) ->
			System.out.println(StringUtil.generateFormOfLottoRank(lottoRank, lottoRankCount)));
	}

	private static void printWinningRate(WinningResult winningResult) {
		System.out.println(String.format(TOTAL_WINNING_RATE_MESSAGE, winningResult.getWinningRate()));
	}
}
