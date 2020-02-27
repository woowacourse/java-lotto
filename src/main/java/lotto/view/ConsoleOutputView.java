package lotto.view;

import java.util.List;

import lotto.domain.lottoTicket.LottoTickets;
import lotto.domain.result.WinningResult;
import lotto.util.StringUtil;

public class ConsoleOutputView {

	private static final int MANUAL_LOTTO_TICKETS_COUNT_INDEX = 0;
	private static final int AUTO_LOTTO_TICKETS_COUNT_INDEX = 1;
	private static final String INPUT_MANUAL_LOTTO_TICKETS_COUNT_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";
	private static final String PURCHASE_LOTTO_COMPLETE_MESSAGE = "수동으로 %d개, 자동으로 %d개를 구매했습니다.";
	private static final String WINNING_RESULT_NOTICE_MESSAGE = "당첨 통계";
	private static final String DIVIDING_LINE = "---------";
	private static final String TOTAL_WINNING_RATE_MESSAGE = "총 수익률은 %d%%입니다.";

	private ConsoleOutputView() {
	}

	public static void printException(String exceptionMessage) {
		System.out.println(exceptionMessage);
	}

	public static void printInputManualLottoTicket() {
		System.out.println(INPUT_MANUAL_LOTTO_TICKETS_COUNT_MESSAGE);
	}

	public static void printPurchasedLottoTicketCount(List<Long> manualAndAutoLottoTicketsCount) {
		System.out.println(String.format(PURCHASE_LOTTO_COMPLETE_MESSAGE,
			manualAndAutoLottoTicketsCount.get(MANUAL_LOTTO_TICKETS_COUNT_INDEX),
			manualAndAutoLottoTicketsCount.get(AUTO_LOTTO_TICKETS_COUNT_INDEX)));
	}

	public static void printPurchasedLottoTickets(LottoTickets purchasedLottoTickets) {
		purchasedLottoTickets.getLottoTickets().stream()
			.map(StringUtil::joiningLottoNumbersAt)
			.forEach(System.out::println);
		System.out.println();
	}

	public static void printWinningLottoResult(WinningResult winningResult) {
		System.out.println();
		System.out.println(WINNING_RESULT_NOTICE_MESSAGE);
		System.out.println(DIVIDING_LINE);
		printWinningLottoTicketByLottoRank(winningResult);
	}

	private static void printWinningLottoTicketByLottoRank(WinningResult winningResult) {
		winningResult.getWinningResult().forEach((lottoRank, lottoRankCount) ->
			System.out.println(StringUtil.generateFormOfLottoRank(lottoRank, lottoRankCount)));
	}

	public static void printWinningRate(long winningResult) {
		System.out.println(String.format(TOTAL_WINNING_RATE_MESSAGE, winningResult));
	}

}
