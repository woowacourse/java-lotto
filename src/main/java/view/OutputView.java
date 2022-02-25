package view;

import domain.*;

import java.util.stream.Collectors;

public class OutputView {

    private static final int ZERO = 0;
    private static final String COUNT_MESSAGE = "%d개를 구매했습니다.\n";
    private static final String STATISTIC_RESULT_MESSAGE = "당첨 통계\n---------";
    private static final String WINNING_MESSAGE = "%d개 일치 (%d원)- %d\n";
    private static final String SECOND_MESSAGE = "%d개 일치, 보너스 볼 일치 (%d원)- %d개\n";
    private static final String PROFIT_RATE_MESSAGE = "총 수익률은 %.2f입니다.\n";
    private static final String DELIMITER = ", ";
    private static final String OPEN_BRACKETS = "[";
    private static final String CLOSE_BRACKETS = "]";

    public static void printCountOfLotto(int count) {
        System.out.printf(COUNT_MESSAGE, count);
    }

    public static void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            printLotto(lotto);
        }
        System.out.println();
    }

    private static void printLotto(Lotto lotto) {
        String LottoNumbers = lotto.getLotto()
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(DELIMITER));

        System.out.println(OPEN_BRACKETS + LottoNumbers + CLOSE_BRACKETS);
    }

    public static void printStatistics(Statistic statistic) {
        System.out.println(STATISTIC_RESULT_MESSAGE);

        statistic.getStatistics().entrySet().stream().filter(statistics -> statistics.getKey().getCount() != ZERO).forEach(statistics -> {
            if (statistics.getKey().hasBonusBall()) {
                System.out.printf(SECOND_MESSAGE, Rank.SECOND.getCount(), Rank.SECOND.getWinningPrice(), statistics.getValue());
                return;
            }
            System.out.printf(WINNING_MESSAGE, statistics.getKey().getCount(), statistics.getKey().getWinningPrice(), statistics.getValue());
        });
    }

    public static void printProfitRate(Statistic statistic, Money money) {
        double profitRate = statistic.getProfitRate(money);
        System.out.printf(PROFIT_RATE_MESSAGE, profitRate);
    }
}