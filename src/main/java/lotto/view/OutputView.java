package lotto.view;

import lotto.domain.LottoPaper;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.Statistics;

public class OutputView {
    private static final String PURCHASE_MESSAGE = "수동으로 %d장, 자동으로 %d장 구매했습니다.\n";
    private static final String STATISTICS_MESSAGE = "당첨 통계\n----------";
    private static final String RESULT_MESSAGE = "%d개 일치 (%d원) - %d개\n";
    private static final String RESULT_SECOND_MESSAGE = "%d개 일치, 보너스볼 일치 (%d원) - %d개\n";

    public static void printPurchase(Money money, int countOfCustomLottos) {
        System.out.printf(PURCHASE_MESSAGE, countOfCustomLottos, money.howManyLotto() - countOfCustomLottos);
    }

    public static void printLottoPaper(LottoPaper lottoPaper) {
        System.out.println(lottoPaper.toString());
    }

    public static void printStatistics(Statistics statistics) {
        System.out.println(STATISTICS_MESSAGE);
        statistics.ranksStatistics()
                .forEach((rank, reward) -> {
                    if (rank == Rank.SECOND) {
                        System.out.printf(RESULT_SECOND_MESSAGE, rank.getCountOfMatch(), rank.getReward(), reward);
                    }
                    if (rank != Rank.SECOND) {
                        System.out.printf(RESULT_MESSAGE, rank.getCountOfMatch(), rank.getReward(), reward);
                    }

                });

        System.out.println(String.format("%d%%", ((int) (statistics.returnOfRate() * 100))));
    }
}
