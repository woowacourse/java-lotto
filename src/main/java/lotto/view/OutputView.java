package lotto.view;

import lotto.domain.*;

public class OutputView {
    private static final String PURCHASE_MESSAGE = "수동으로 %d장, 자동으로 %d장 구매했습니다.\n";
    private static final String STATISTICS_MESSAGE = "당첨 통계\n----------";
    private static final String RESULT_MESSAGE = "%d개 일치 (%d원) - %d개\n";
    private static final String RESULT_SECOND_MESSAGE = "%d개 일치, 보너스볼 일치 (%d원) - %d개\n";
    private static final String PERCENT_FORMAT = "%d%%";
    private static final int PERCENT = 100;

    public static void printPurchase(Money money, CustomLottoCount customLottoCount) {
        System.out.printf(PURCHASE_MESSAGE, customLottoCount.getCount(), money.howManyLotto() - customLottoCount.getCount());
    }

    public static void printLottoPaper(LottoPaper lottoPaper) {
        System.out.println(lottoPaper.toString());
    }

    public static void printStatistics(Statistics statistics) {
        System.out.println(STATISTICS_MESSAGE);

        statistics.ranksStatistics().forEach(OutputView::printRankStatistics);
        System.out.println(String.format(PERCENT_FORMAT, ((int) (statistics.returnOfRate() * PERCENT))));
    }

    private static void printRankStatistics(Rank rank, Long count) {
        System.out.printf(rank != Rank.SECOND ? RESULT_MESSAGE : RESULT_SECOND_MESSAGE, rank.getCountOfMatch(), rank.getReward(), count);
    }

}
