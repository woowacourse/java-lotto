package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.Lottos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class OutputView {

    public static final String REGULAR_RESULT_EXPRESSION = "%d개 일치, (%d원) - %d개" + System.lineSeparator();
    public static final String BONUS_RESULT_EXPRESSION = "5개 일치, 보너스볼일치(%d원) - %d개" + System.lineSeparator();
    public static final String PROFIT_RATE_EXPRESSION = "총 수익률은 %.2f입니다." + System.lineSeparator();
    public static final String PURCHASED_LOTTO_COUNT_ALARM = "수동으로 %d장, 자동으로 %d개를 구매했습니다." + System.lineSeparator();
    public static final String LOTTO_STATISTICS_PREFIX = "당첨 통계" + System.lineSeparator() + "---------";

    public static void printInputMessage(String message) {
        System.out.println(message);
    }

    public static void printPurchasedLottos(Lottos purchasedManualLottos, Lottos purchasedAutoLottos) {
        System.out.printf(PURCHASED_LOTTO_COUNT_ALARM, purchasedAutoLottos.getSize(), purchasedAutoLottos.getSize());

        for (Lotto lotto : getLottoBunch(purchasedManualLottos, purchasedAutoLottos)) {
            System.out.println(lotto.getNumbers());
        }
        System.out.println();
    }

    private static List<Lotto> getLottoBunch(Lottos purchasedManualLottos, Lottos purchasedAutoLottos) {
        List<Lotto> lottoBunch = new ArrayList<>();
        lottoBunch.addAll(purchasedManualLottos.getLottoBunch());
        lottoBunch.addAll(purchasedAutoLottos.getLottoBunch());

        return lottoBunch;
    }

    public static void printLottoStatistics(Map<LottoRank, Integer> statistics, double profitRate) {
        System.out.println(LOTTO_STATISTICS_PREFIX);
        for (LottoRank rank : statistics.keySet()) {
            printSingleResult(rank, statistics.get(rank));
        }
        System.out.printf(PROFIT_RATE_EXPRESSION, profitRate);
    }

    private static void printSingleResult(LottoRank rank, int rankCount) {
        if (rank.equals(LottoRank.NONE)) {
            return;
        }
        if (rank.equals(LottoRank.SECOND)) {
            System.out.printf(BONUS_RESULT_EXPRESSION, rank.getPrizeMoney(), rankCount);
            return;
        }
        System.out.printf(REGULAR_RESULT_EXPRESSION, (int) rank.getMatchingCount(), rank.getPrizeMoney(), rankCount);
    }
}