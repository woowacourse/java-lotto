package lotto.view;

import lotto.domain.*;

import java.util.List;
import java.util.stream.Collectors;

import static lotto.view.RankMessage.getRankMessages;

public class OutputView {
	public static final String DEFAULT_STATISTICS_FORMAT = "%d개 일치 (%d원)- ";
	public static final String STATISTICS_FORMAT_FOR_SECOND = "%d개 일치, 보너스 볼 일치(%d원)- ";

	private OutputView() {
	}

	public static void printLottoPurchaseSummaryBasedOn(Lottos lottos, LottoMachine lottoMachine) {
		System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", lottoMachine.getManualLottoQuantityAsInt(), lottoMachine.getAutoLottoQuantityAsInt());
		for (Lotto lotto : lottos.getLottos()) {
			printLottoSummaryOf(lotto);
		}
		System.out.println();
	}

	private static void printLottoSummaryOf(Lotto lotto) {
		System.out.printf("[%s]\n", lottoSummaryOf(lotto));
	}

	private static String lottoSummaryOf(Lotto lotto) {
		return lotto.getLottoNumbers()
				.stream()
				.map(LottoNumber::getNumberAsString)
				.collect(Collectors.joining(", "));
	}

	public static void printResultStatisticsBasedOn(LottoStatistics lottoStatistics) {
		System.out.println("당첨 통계");
		System.out.println("---------");
		List<Integer> statistics = lottoStatistics.getWinCountByRank();
		List<String> rankMessages = getRankMessages();
		for (int i = 0; i < statistics.size(); i++) {
			System.out.printf("%s %d개\n", rankMessages.get(i), statistics.get(i));
		}
		System.out.printf("총 수익률은 %.2f입니다.\n", lottoStatistics.getProfitRate());
	}
}
