
public class OutputView {

    private static final int ZERO = 0;
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

        statistic.getStatistics().keySet().stream().filter(rank -> !rank.equals(Rank.SIXTH)).forEach(rank -> {
            if(rank.hasBonusBall()){
                System.out.printf(SECOND_MESSAGE, rank.getCount(), rank.getWinningPrice(), statistic.getStatistics().get(rank));
                return;
            }
            System.out.printf(WINNING_MESSAGE, rank.getCount(), rank.getWinningPrice(), statistic.getStatistics().get(rank));
        });
    }

    public static void printProfitRate(Statistic statistic, Money money) {
        double profitRate = statistic.getProfitRate(money);
        System.out.printf(PROFIT_RATE_MESSAGE, profitRate);
    }
}