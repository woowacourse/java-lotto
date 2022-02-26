package view;

import java.util.Map;
import java.util.stream.Collectors;

import domain.Lotto;
import domain.LottoResult;
import domain.Lottos;
import domain.Rank;

public class OutputView {
	private static final String ERROR_MESSAGE = "[ERROR] ";

	private OutputView() {
	}

	public static void printGuideMessage(String message) {
		System.out.println(message);
	}

	public static void printErrorMessage(String errorMessage) {
		System.out.println(ERROR_MESSAGE + errorMessage);
	}

	public static void printLottoCount(int lottoCount) {
		System.out.println(String.format("%d개를 구매했습니다.", lottoCount));
	}

	public static void printLottos(Lottos lottos) {
		lottos.getLottos().stream()
			.forEach(OutputView::printLotto);
		System.out.println();
	}

	private static void printLotto(Lotto lotto) {
		String format = "[%s]";
		System.out.println(String.format(format, lotto.getLotto().stream()
			.map(lottoNumber -> String.valueOf(lottoNumber.getLottoNumber()))
			.collect(Collectors.joining(", "))));
	}

	public static void printRankCounts(LottoResult lottoResult) {
		Map<Rank, Long> ranks = lottoResult.getRanks();
		System.out.println("\n당첨 통계\n---------");
		ranks.forEach(OutputView::printRankCount);
	}

	private static void printRankCount(Rank rank, Long count) {
		String format = "%d개 일치 (%d원)- %d개";
		if (rank.isBonus()) {
			format = "%d개 일치, 보너스 볼 일치(%d원)- %d개";
		}
		System.out.println(String.format(format, rank.getMatch(), rank.getMoney(), count));
	}

	public static void printProfitRate(double profitRate) {
		String format = "총 수익률은 %.2f입니다.";
		System.out.println(String.format(format, profitRate));
	}
}
