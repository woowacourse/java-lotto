import java.util.EnumMap;

public class OutputView {

    private static final String COUNT_MESSAGE = "%d개를 구매했습니다.\n";
    private static final String STATISTIC_RESULT_MESSAGE = "당첨 통계\n---------";
    private static final String WINNING_MESSAGE = "%d개 일치 (%d원)- %d\n";
    private static final String SECOND_MESSAGE = "%d개 일치, 보너스 볼 일치 (%d원)- %d개\n";
    private static final String PROFIT_RATE_MESSAGE = "총 수익률은 %.2f입니다.\n";

    public static void printCountOfLotto(int count) {
        System.out.printf(COUNT_MESSAGE, count);
    }

    public static void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto);
        }
        System.out.println();
    }

    public static void printStatistics(Statistic statistic) {
        System.out.println(STATISTIC_RESULT_MESSAGE);
        EnumMap<Rank, Integer> statistics = statistic.getStatistics();
        statistics.keySet().stream()
            .filter(rank -> !rank.equals(Rank.SIXTH))
            .forEach(rank -> printStatisticsMessage(rank, statistics.get(rank)));
    }

    private static void printStatisticsMessage(Rank rank, int count) {
        System.out.printf(selectPrintMessageType(rank), rank.getCount(), rank.getWinningPrice(),
            count);
    }

    private static String selectPrintMessageType(Rank rank) {
        if (rank.hasBonusBall()) {
            return SECOND_MESSAGE;
        }
        return WINNING_MESSAGE;
    }

    public static void printProfitRate(double profitRate) {
        System.out.printf(PROFIT_RATE_MESSAGE, profitRate);
    }
}
