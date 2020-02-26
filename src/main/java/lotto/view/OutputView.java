package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoManager;
import lotto.domain.Money;

import java.util.List;

public class OutputView {
    private static final String NEW_LINE = "\n";
    private static final String INCOME_RATE_MESSAGE_FORMAT = "총 수익률은 %d %s 입니다.";
    private static final String BUY_MESSAGE_FORMAT = "수동으로 %d개, 자동으로 %d개를 구매했습니다";
    private static final String PERCENT = "%";

    public static void printLotteries(List<Lotto> lotteries, int userLottoCount) {
        printLottoAmounts(lotteries.size(), userLottoCount);
        StringBuilder builder = new StringBuilder();
        for (Lotto lotto : lotteries) {
            builder.append(lotto + NEW_LINE);
        }
        System.out.println(builder.toString());
    }

    private static void printLottoAmounts(int totalLottoCount, int userLottoCount) {
        int autoLottoCount = totalLottoCount - userLottoCount;
        System.out.println(String.format(BUY_MESSAGE_FORMAT, userLottoCount, autoLottoCount));
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
