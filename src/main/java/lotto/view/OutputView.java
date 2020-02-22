package lotto.view;

import java.util.Map;

import lotto.domain.LottoCount;
import lotto.domain.LottoRank;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.domain.WinningResult;
import lotto.util.StringUtil;

public class OutputView {
	private static final String STATISTICS_MESSAGE = "\n당첨 통계\n---------";
	private static final String LOTTO_COUNT_MESSAGE = "%s개를 구매했습니다.\n";
	private static final char LOTTO_NUMBER_OPENER = '[';
	private static final char LOTTO_NUMBER_CLOSER = ']';
	private static final String TOTAL_PROFIT_MESSAGE = "총 수익률은 %d%% 입니다.";

	private OutputView() {
	}

	public static void printLottoCount(LottoCount count) {
		System.out.printf(LOTTO_COUNT_MESSAGE, count);
	}

	public static void printLottos(LottoTickets lottoTickets) {
		StringBuffer stringBuffer = new StringBuffer();
		for (LottoTicket lottoTicket : lottoTickets) {
			String lottoData = StringUtil.parseBalls(lottoTicket.getLottoBalls());
			stringBuffer.append(LOTTO_NUMBER_OPENER);
			stringBuffer.append(lottoData);
			stringBuffer.append(LOTTO_NUMBER_CLOSER);
			stringBuffer.append(System.lineSeparator());
		}
		System.out.println(stringBuffer);
	}

	public static void printStatistics(WinningResult winningResult, Money money) {
		printResultIntro();
		printMatchingResult(winningResult);
		printProfitsResult(winningResult, money);
	}

	private static void printResultIntro() {
		System.out.println(STATISTICS_MESSAGE);
	}

	private static void printMatchingResult(WinningResult winningResult) {
		Map<LottoRank, Long> result = winningResult.getWinningResult();
		for (LottoRank lottoRank : result.keySet()) {
			printStatisticsOneLine(lottoRank, result.get(lottoRank));
		}
	}

	private static void printStatisticsOneLine(LottoRank lottoRank, Long count) {
		String oneLineMatchingResult = StringUtil.parseLottoMatchingResult(lottoRank, count);
		System.out.print(oneLineMatchingResult);
	}

	private static void printProfitsResult(WinningResult winningResult, Money money) {
		System.out.printf(TOTAL_PROFIT_MESSAGE, winningResult.getProfitRate(money));
	}

	public static void printExceptionMessage(String message) {
		System.out.println(message);
	}
}
