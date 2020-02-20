package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.MatchResult;
import lotto.util.TextUtil;

/**
 * 출력 클래스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/19
 */
public class OutputView {
	public static final String TOTAL_BUY_FORMAT_MESSAGE = "%d개를 구입했습니다.\n";
	public static final String LOTTO_FORMAT_MESSAGE = "[%s]\n";
	public static final String STATISTICS_TITLE_MESSAGE = "당첨 통계\n---------";
	public static final String TOTAL_PROFIT_FORMAT_MESSAGE = "총 수익률은 %d%%입니다.\n";

	private OutputView() {
	}

	public static void printLottos(Lottos lottos) {
		System.out.printf(TOTAL_BUY_FORMAT_MESSAGE, lottos.size());
		for (Lotto lotto : lottos.getLottos()) {
			printLotto(lotto);
		}
		System.out.println();
	}

	private static void printLotto(Lotto lotto) {
		System.out.printf(LOTTO_FORMAT_MESSAGE, TextUtil.generateLottoTextWithComma(lotto));
	}

	public static void printStatistics(MatchResult matchResult) {
		System.out.println(STATISTICS_TITLE_MESSAGE);
		System.out.print(TextUtil.generateMatchResultText(matchResult));
	}

	public static void printTotalProfits(long totalProfits) {
		System.out.printf(TOTAL_PROFIT_FORMAT_MESSAGE, totalProfits);
	}

	public static void printError(String message) {
		System.out.println(message);
	}
}
