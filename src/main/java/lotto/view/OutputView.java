package lotto.view;

import java.util.List;
import java.util.Map;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Rank;

public class OutputView {

	private static final String PERCENT = "%";
	private static final String INCOME_RATE_MESSAGE_FORMAT = "총 수익률은 %d%s 입니다.";
	private static final String BUY_MESSAGE_FORMAT = "%d개를 구매했습니다";
	private static final String RESULT_MESSAGE_FORMAT = "%d개 일치 (%d원) - %d개";
	private static final String SECOND_WIN_ADDITIONAL_MESSAGE = ", 보너스볼 일치";
	private static final String WINNING_STATISTICS_MESSAGE = "당첨 통계%s---------";
	private static final int INSERT_OFFSET = 5;

	public static void printLotteris(List<Lotto> lotteris) {
		printLottoCount(lotteris.size());
		StringBuilder builder = new StringBuilder();
		lotteris.forEach(lotto -> {
			builder.append(lotto);
			builder.append(System.lineSeparator());
		});
		System.out.println(builder);
	}

	private static void printLottoCount(int count) {
		System.out.println(String.format(BUY_MESSAGE_FORMAT, count));
	}

	public static void printResult(LottoResult result) {
		Map<Rank, Integer> results = result.getResult();
		System.out.println(String.format(WINNING_STATISTICS_MESSAGE, System.lineSeparator()));
		for (Rank rank : Rank.valuesOnlyWin()) {
			System.out.println(getResultMessage(results, rank));
		}
		printIncomeRate(result);
	}

	private static String getResultMessage(Map<Rank, Integer> results, Rank rank) {
		String message = String.format(RESULT_MESSAGE_FORMAT, rank.getHitCount(),
			rank.getReward(), results.get(rank));
		if (rank == Rank.SECOND) {
			message = new StringBuilder(message)
				.insert(INSERT_OFFSET, SECOND_WIN_ADDITIONAL_MESSAGE).toString();
		}
		return message;
	}

	private static void printIncomeRate(LottoResult result) {
		System.out.println(String.format(INCOME_RATE_MESSAGE_FORMAT, result.getIncomeRate(), PERCENT));
	}
}
