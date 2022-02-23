import java.util.Map;

public class OutputView {

    private static final String COUNT_MESSAGE = "%d개를 구매했습니다.\n";
    private static final String STATISTIC_RESULT_MESSAGE = "당첨 통계\n---------";
    private static final String WINNING_MESSAGE = "%d개 일치 (%d원)- %d\n";
    private static final String SECOND_MESSAGE = "%d개 일치, 보너스 볼 일치 (%d원)- %d개\n";

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

        for (Map.Entry<Rank, Integer> statistics : statistic.getStatistics().entrySet()) {
            if (statistics.getKey().getCount() == 0) {
                continue;
            }
            if (statistics.getKey().hasBonusBall()) {
                System.out.printf(SECOND_MESSAGE, Rank.SECOND.getCount(), Rank.SECOND.getWinningPrice(), statistics.getValue());
                continue;
            }

            System.out.printf(WINNING_MESSAGE, statistics.getKey().getCount(), statistics.getKey().getWinningPrice(), statistics.getValue());
        }

    }
}