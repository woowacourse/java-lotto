package lotto.view;

import java.util.List;

import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.domain.Result;

public class OutputView {

	public static final String NEW_LINE = "\n";
	public static final String RESULT_MESSAGE_FORMAT = "%d개 일치 (%d원) - %d개";
	public static final String INCOME_RATE_MESSAGE_FORMAT = "총 수익률은 %d% 입니다.";
	public static final String BUY_MESSAGE_FORMAT = "%d개를 구매했습니다";

	private static void printLottoCount(int count) {
		System.out.println(String.format(BUY_MESSAGE_FORMAT, count));
	}

	public static void printLotteris(List<Lotto> lotteris) {
		printLottoCount(lotteris.size());
		StringBuilder builder = new StringBuilder();
		for (Lotto lotto : lotteris) {
			builder.append(lotto + NEW_LINE);
		}
		System.out.println(builder.toString());
	}

	public static void printResult(Money money) {
		for (Result result : Result.values()) {
			int count = result.getCount();
			int hitCount = result.getHitCount();
			long reward = result.getReward();
			System.out.println(String.format(RESULT_MESSAGE_FORMAT, hitCount, reward, count));
		}
		int incomeRate = money.calculateIncomeRate(Result.calculateTotalReward());
		System.out.println(String.format(INCOME_RATE_MESSAGE_FORMAT, incomeRate));
	}
}
