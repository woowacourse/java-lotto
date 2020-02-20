package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Money;

import java.util.List;

public class OutputView {
    public static final String NEW_LINE = "\n";
    public static final String RESULT_MESSAGE_FORMAT = "%d개 일치 (%d원) - %d개";
    public static final String INCOME_RATE_MESSAGE_FORMAT = "총 수익률은 %d% 입니다.";
    public static final String BUY_MESSAGE_FORMAT = "%d개를 구매했습니다";

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

    public static void printResult(Money money) {
        for (LottoResult lottoResult : LottoResult.values()) {
            int ticketCount = lottoResult.getTicketCount();
            int hitCount = lottoResult.getHitCount();
            long reward = lottoResult.getReward();
            System.out.println(String.format(RESULT_MESSAGE_FORMAT, hitCount, reward, ticketCount));
        }
        printIncomeRate(money);
    }

    private static void printIncomeRate(Money money) {
        int incomeRate = money.calculateIncomeRate(LottoResult.calculateTotalReward());
        System.out.println(String.format(INCOME_RATE_MESSAGE_FORMAT, incomeRate));
    }
}
