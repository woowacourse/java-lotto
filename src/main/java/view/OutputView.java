package view;

import java.util.Map;
import java.util.stream.Collectors;

import domain.Lotto;
import domain.Lottos;
import domain.Rank;

public class OutputView {
	private OutputView() {
	}

	public static void printLottoCount(int lottoCount) {
		System.out.println(String.format("%d개를 구매했습니다.", lottoCount));
	}

	public static void printLottos(Lottos lottos) {
		lottos.getLottos().stream()
			.forEach(OutputView::printLotto);
	}

	private static void printLotto(Lotto lotto) {
		System.out.println(lotto.getLotto().stream()
			.map(lottoNumber -> String.valueOf(lottoNumber.getLottoNumber()))
			.collect(Collectors.joining(", ")));
	}

	public static void printRankCounts(Map<Rank, Integer> ranks) {
		ranks.forEach(OutputView::printRankCount);
	}

	private static void printRankCount(Rank rank, Integer count) {
		String format = "%d개 일치 (%d)- %d개";
		System.out.println(String.format(format, rank.getMatch(), rank.getMoney(), count));
	}

	public static void printProfitRate(double profitRate) {
		String format = "총 수익률은 %.2f입니다.";
		System.out.println(String.format(format, profitRate));
	}
}
