package lotto.view;

import lotto.domain.ticket.Lotto;
import lotto.domain.ticket.LottoNumber;
import lotto.domain.ticket.Lottos;
import lotto.domain.production.LottoMachine;
import lotto.domain.result.LottoStatistics;

import java.util.List;
import java.util.stream.Collectors;

import static lotto.view.RankMessage.getRankMessages;

public class OutputView {
	public static final String DEFAULT_STATISTICS_FORMAT = "%d개 일치 (%d원)-";
	private static final String DELIMITER = ", ";
	public static final String STATISTICS_FORMAT_FOR_SECOND = "%d개 일치" + DELIMITER + "보너스 볼 일치(%d원)-";

	private OutputView() {
	}

	public static void printLottoPurchaseSummaryBasedOn(Lottos lottos, LottoMachine lottoMachine) {
		int manualLottoQuantity = lottoMachine.getManualLottoQuantityAsInt();
		int autoLottoQuantity = lottoMachine.getAutoLottoQuantityAsInt();
		System.out.printf("수동으로 %d장" + DELIMITER + "자동으로 %d개를 구매했습니다.\n", manualLottoQuantity, autoLottoQuantity);
		for (Lotto lotto : lottos.toList()) {
			printLottoSummaryOf(lotto);
		}
		System.out.println();
	}

	private static void printLottoSummaryOf(Lotto lotto) {
		String lottoSummary = getLottoSummaryOf(lotto);
		System.out.printf("[%s]\n", lottoSummary);
	}

	private static String getLottoSummaryOf(Lotto lotto) {
		return lotto.getLottoNumbers()
				.stream()
				.map(LottoNumber::getNumberAsString)
				.collect(Collectors.joining(DELIMITER));
	}

	public static void printResultStatisticsBasedOn(LottoStatistics lottoStatistics) {
		System.out.println("당첨 통계");
		System.out.println("---------");
		List<Integer> statistics = lottoStatistics.getWinCountsByRank();
		List<String> rankMessages = getRankMessages();
		for (int i = 0; i < statistics.size(); i++) {
			System.out.printf("%s %d개\n", rankMessages.get(i), statistics.get(i));
		}
		float profitRate = lottoStatistics.getProfitRate();
		System.out.printf("총 수익률은 %.2f입니다.\n", profitRate);
	}
}
