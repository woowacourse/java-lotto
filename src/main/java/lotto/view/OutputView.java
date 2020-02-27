package lotto.view;

import java.util.Map;
import java.util.Set;

import lotto.domain.result.LottoRank;
import lotto.domain.result.TotalResult;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTickets;
import lotto.domain.ticket.PurchaseLottoCount;
import lotto.util.PrintTextUtil;
import lotto.util.StringUtil;

public class OutputView {
	private static final String STATISTICS_MESSAGE = "\n당첨 통계\n---------";
	private static final String LOTTO_COUNT_MESSAGE = "수동 %s개, 자동 %s개를 구매했습니다.\n";
	private static final char LOTTO_NUMBER_OPENER = '[';
	private static final char LOTTO_NUMBER_CLOSER = ']';
	private static final String TOTAL_PROFIT_MESSAGE = "총 수익률은 %d%% 입니다.";

	private OutputView() {
	}

	public static void printLottoCount(PurchaseLottoCount purchaseCount) {
		System.out.printf(LOTTO_COUNT_MESSAGE, purchaseCount.getManualCount(), purchaseCount.calculateAutoCount());
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

	public static void printStatistics(TotalResult totalResult) {
		printResultIntro();
		printMatchingResult(totalResult);
		printProfitsResult(totalResult);
	}

	private static void printResultIntro() {
		System.out.println(STATISTICS_MESSAGE);
	}

	private static void printMatchingResult(TotalResult totalResult) {
		Map<LottoRank, Long> result = totalResult.getWinningResult();
		Set<LottoRank> lottoRanks = result.keySet();

		for (LottoRank lottoRank : lottoRanks) {
			printStatisticsOneLine(lottoRank, result.get(lottoRank));
		}
	}

	private static void printStatisticsOneLine(LottoRank lottoRank, Long count) {
		String oneLineMatchingResult = PrintTextUtil.parseLottoMatchingResult(lottoRank, count);
		System.out.print(oneLineMatchingResult);
	}

	private static void printProfitsResult(TotalResult totalResult) {
		System.out.printf(TOTAL_PROFIT_MESSAGE, totalResult.getProfitRate());
	}

	public static void printExceptionMessage(String message) {
		System.out.println(message);
	}
}
