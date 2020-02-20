package lotto.view;

import java.util.Map;

import lotto.domain.Lotto;
import lotto.domain.LottoCount;
import lotto.domain.LottoRank;
import lotto.domain.Lottos;
import lotto.domain.TotalResult;

public class OutputView {

	private static final String STATISTICS_MESSAGE = "\n당첨 통계\n---------";
	private static final String LOTTO_COUNT_MESSAGE = "\n%d개를 구매했습니다.\n";
	private static final String TOTAL_PROFIT_MESSAGE = "총 수익률은 %d%% 입니다.";
	private static final String BONUS_BALL_MESSAGE = ", 보너스 볼 일치";
	private static final String TOTAL_PRIZE_AND_COUNT_MESSAGE = " (%d원) - %d개\n";
	private static final String MATCH_COUNT_MESSAGE = "%d개 일치";

	private OutputView() {
	}

	public static void printLottoCount(LottoCount count) {
		System.out.printf(LOTTO_COUNT_MESSAGE, count.getLottoCount());
	}

	public static void printLottos(Lottos lottos) {
		for (Lotto lotto : lottos) {
			System.out.println(lotto.getLotto());
		}
	}

	public static void printStatistics(TotalResult totalResult) {
		System.out.println(STATISTICS_MESSAGE);
		Map<LottoRank, Integer> lottoResult = totalResult.getLottoResult();
		for (LottoRank lottoRank : lottoResult.keySet()) {
			printStatisticsOneLine(lottoRank, lottoResult.get(lottoRank));
		}
		System.out.printf(TOTAL_PROFIT_MESSAGE, totalResult.getProfitRate());
	}

	private static void printStatisticsOneLine(LottoRank lottoRank, Integer count) {
		System.out.printf(MATCH_COUNT_MESSAGE, lottoRank.getMatchCount());
		if (lottoRank == LottoRank.SECOND) {
			System.out.print(BONUS_BALL_MESSAGE);
		}
		System.out.printf(TOTAL_PRIZE_AND_COUNT_MESSAGE, lottoRank.getPrize(), count);
	}

	public static void printExceptionMessage(String message) {
		System.out.println(message);
	}
}
