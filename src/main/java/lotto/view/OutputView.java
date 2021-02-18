package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.Lottos;

import java.util.List;
import java.util.Map;

public class OutputView {

    public static final String REGULAR_RESULT_EXPRESSION = "%d개 일치, (%d원) - %d개";
    public static final String BONUS_RESULT_EXPRESSION = "5개 일치, 보너스볼일치(%d원) - %d개";
    public static final String PROFIT_RATE_EXPRESSION = "총 수익률은 %.2f입니다.";
    public static final String PURCHASED_LOTTO_COUNT_ALARM = "%d를 구매하였습니다." + System.lineSeparator();

    public static void printInputMessage(String message) {
        System.out.println(message);
    }

    public static void printPurchasedLottos(Lottos lottos) {
        List<Lotto> lottoBunch = lottos.getLottoBunch();
        System.out.printf(PURCHASED_LOTTO_COUNT_ALARM, lottoBunch.size());
        for (Lotto lotto : lottoBunch) {
            System.out.println(lotto.getNumbers());
        }
        System.out.println();
    }

    public static void printLottoStatistics(Map<LottoRank, Integer> statistics, double profitRate) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        for (LottoRank key : statistics.keySet()) {
            printSingleResult(key, statistics.get(key));
        }
        System.out.printf(PROFIT_RATE_EXPRESSION + System.lineSeparator(), profitRate);
    }

    private static void printSingleResult(LottoRank key, int value) {
        if (key.equals(LottoRank.NONE)) {
            return;
        }
        if (key.equals(LottoRank.SECOND)) {
            System.out.printf(BONUS_RESULT_EXPRESSION + System.lineSeparator(),
                    key.getPrizeMoney(), value);
            return;
        }
        System.out.printf(REGULAR_RESULT_EXPRESSION + System.lineSeparator(),
                (int) key.getMatchingCount(), key.getPrizeMoney(), value);
    }
}