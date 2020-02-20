package lotto.view;

import java.util.Map;

import lotto.domain.Lotto;
import lotto.domain.LottoCount;
import lotto.domain.LottoRank;
import lotto.domain.Lottos;
import lotto.domain.TotalResult;

public class OutputView {

	public static final String STATISTICS_MESSAGE = "\n당첨 통계\n---------";
	private static final String LOTTO_COUNT_MESSAGE = "\n%d개를 구매했습니다.\n";

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
		System.out.printf("총 수익률은 %d%% 입니다.", totalResult.getProfitRate());
	}

	private static void printStatisticsOneLine(LottoRank lottoRank, Integer count) {
		System.out.printf("%d개 일치", lottoRank.getMatchCount());
		if(lottoRank == LottoRank.SECOND) {
			System.out.print(", 보너스 볼 일치");
		}
		System.out.printf(" (%d원) - %d개\n", lottoRank.getPrize(), count);
	}

}
