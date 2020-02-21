package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoManager;
import lotto.domain.Money;

import java.util.List;

public class OutputView {
	public static final String NEW_LINE = "\n";
	public static final String INCOME_RATE_MESSAGE_FORMAT = "총 수익률은 %d %s 입니다.";
	public static final String BUY_MESSAGE_FORMAT = "%d개를 구매했습니다";
	public static final String PERCENT = "%";

	public static void printLotteris(List<Lotto> lotteris) {
		printLottoAmounts(lotteris.size());
		StringBuilder builder = new StringBuilder();
        for (Lotto lotto : lotteris) {
            builder.append(lotto + NEW_LINE);
        }
        System.out.println(builder.toString());
    }

    private static void printLottoAmounts(int count) {
        System.out.println(String.format(BUY_MESSAGE_FORMAT, count));
    }

    public static void printResult(Money money, LottoManager lottoManager) {
        lottoManager.getResult();
        printIncomeRate(money, lottoManager);
    }

    private static void printIncomeRate(Money money, LottoManager lottoManager) {
        int incomeRate = money.calculateIncomeRate(lottoManager.calculateTotalReward());
        System.out.println(String.format(INCOME_RATE_MESSAGE_FORMAT, incomeRate, PERCENT));
    }
}
