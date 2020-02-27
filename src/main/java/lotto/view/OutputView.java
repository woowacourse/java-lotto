package lotto.view;

import lotto.domain.Customer;
import lotto.domain.Lotto;
import lotto.domain.LottoManager;

import java.util.List;

public class OutputView {
    private static final String NEW_LINE = "\n";
    private static final String INCOME_RATE_MESSAGE_FORMAT = "총 수익률은 %d %s 입니다.";
    private static final String BUY_MESSAGE_FORMAT = "수동으로 %d개, 자동으로 %d개를 구매했습니다";
    private static final String PERCENT = "%";

    public static void printLottos(List<Lotto> lottos) {
        StringBuilder builder = new StringBuilder();
        for (Lotto lotto : lottos) {
            builder.append(lotto + NEW_LINE);
        }
        System.out.println(builder.toString());
    }

    public static void printLottoAmounts(Customer customer) {
        System.out.println(String.format(
                BUY_MESSAGE_FORMAT, customer.getManualLottoCount(), customer.calculatorAutoLottoCount()));
    }

    public static void printResult(Customer customer, LottoManager lottoManager) {
        System.out.println(lottoManager.getResult());
        printIncomeRate(customer, lottoManager);
    }

    private static void printIncomeRate(Customer customer, LottoManager lottoManager) {
        int incomeRate = customer.calculateIncomeRate(lottoManager.calculateTotalReward());
        System.out.println(String.format(INCOME_RATE_MESSAGE_FORMAT, incomeRate, PERCENT));
    }
}
