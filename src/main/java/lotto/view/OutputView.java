package lotto.view;

import java.util.List;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Money;

public class OutputView {

	private static final String NEW_LINE = "\n";
	private static final String PERCENT = "%";
	private static final String INCOME_RATE_MESSAGE_FORMAT = "총 수익률은 %d%s 입니다.";
	private static final String BUY_MESSAGE_FORMAT = "%d개를 구매했습니다";

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

	public static void printResult() {
		System.out.println("당첨 통계\n---------");
		for (LottoResult lottoResult : LottoResult.values()) {
			System.out.println(lottoResult);
		}
	}

	public static void printIncomeRate(Money money) {
		long totalReward = LottoResult.calculateTotalReward();
		int incomeRate = money.calculateIncomeRate(totalReward);
		System.out.println(String.format(INCOME_RATE_MESSAGE_FORMAT, incomeRate, PERCENT));
	}
}
