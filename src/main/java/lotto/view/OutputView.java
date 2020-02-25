package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoManager;
import lotto.domain.Money;

import java.util.List;

public class OutputView {
    public static final String NEW_LINE = "\n";
    public static final String INCOME_RATE_MESSAGE_FORMAT = "총 수익률은 %d %s 입니다.";
    public static final String BUY_MESSAGE_FORMAT = "수동으로 %d개, 자동으로 %d개를 구매했습니다";
    public static final String PERCENT = "%";

    public static void printLotteries(List<Lotto> lotteries, int manualCount) {
        printLottoAmounts(lotteries.size(), manualCount);
        StringBuilder builder = new StringBuilder();
        for (Lotto lotto : lotteries) {
            builder.append(lotto + NEW_LINE);
        }
        System.out.println(builder.toString());
    }

    private static void printLottoAmounts(int totalLottoCount, int manualCount) {
        int autoLottoCount = totalLottoCount - manualCount;
        System.out.println(String.format(BUY_MESSAGE_FORMAT, manualCount, autoLottoCount));
    }

    public static void printResult(Money money, LottoManager lottoManager) {
        System.out.println(lottoManager.getResult());
        printIncomeRate(money, lottoManager);
    }

    private static void printIncomeRate(Money money, LottoManager lottoManager) {
        int incomeRate = money.calculateIncomeRate(lottoManager.calculateTotalReward());
        System.out.println(String.format(INCOME_RATE_MESSAGE_FORMAT, incomeRate, PERCENT));
    }
}
