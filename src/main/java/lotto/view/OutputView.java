package lotto.view;

import java.util.List;
import java.util.Map;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Rank;

public class OutputView {

	private static final String NEW_LINE = "\n";
	private static final String PERCENT = "%";
	private static final String INCOME_RATE_MESSAGE_FORMAT = "총 수익률은 %d%s 입니다.";
	private static final String BUY_MESSAGE_FORMAT = "%d개를 구매했습니다";
	private static final String RESULT_MESSAGE_FORMAT = "%d개 일치 (%d원) - %d개\n";

	public static void printLotteris(List<Lotto> lotteris) {
		printLottoCount(lotteris.size());
		StringBuilder builder = new StringBuilder();
		for (Lotto lotto : lotteris) {
			builder.append(lotto + NEW_LINE);
		}
		System.out.println(builder);
	}

	private static void printLottoCount(int count) {
		System.out.println(String.format(BUY_MESSAGE_FORMAT, count));
	}

	public static void printResult(LottoResult result) {
		Map<Rank, Integer> results = result.getResult();
		System.out.println("당첨 통계\n---------");
		for (Rank rank : Rank.valuesOnlyWin()) {
			System.out.printf(RESULT_MESSAGE_FORMAT, rank.getHitCount(),
				rank.getReward(), results.get(rank));
		}
		printIncomeRate(result);
	}

	private static void printIncomeRate(LottoResult result) {
		System.out.println(String.format(INCOME_RATE_MESSAGE_FORMAT, result.getIncomeRate(), PERCENT));
	}
}
