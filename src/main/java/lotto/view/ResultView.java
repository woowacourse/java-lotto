package lotto.view;

import lotto.model.Lotto;
import lotto.model.LottoStatistics;
import lotto.model.Lottos;
import lotto.model.Rank;

public class ResultView {

    private static final String BUY_COUNT_MESSAGE = "개를 구매했습니다.";
    private static final String CORRESPOND_FORMAT_MESSAGE = "%d개 일치";
    private static final String PRICE_COUNT_FORMAT_MESSAGE = " (%d원) - %d개\n";
    private static final String BONUS_BALL_NOT_CORRESPOND_MESSAGE = "";
    private static final String STATISTICS_MESSAGE = "당첨통계\n---------";
    private static final String BONUS_BALL_CORRESPOND_MESSAGE = ", 보너스 볼 일치";
    private static final String REVENUE_FORMAT_MESSAGE = "총 수익률은 %.2f입니다.";

    public static void printResult(Lottos lottos) {
        printLottoCount(lottos);
        printBuyingLottos(lottos);
    }

    private static void printLottoCount(Lottos lottos) {
        System.out.println(lottos.findLottoCount() + BUY_COUNT_MESSAGE);
    }

    private static void printBuyingLottos(Lottos lottos) {
        lottos.getLottos()
            .forEach(ResultView::printBuyingLotto);
    }

    private static void printBuyingLotto(Lotto lotto) {
        System.out.println(lotto.getNumbers());
    }

    public static void printTotalResult(LottoStatistics statistics, int amount) {
        System.out.println('\n' + STATISTICS_MESSAGE);
        statistics.getRankMap()
            .forEach((rank, count) -> System.out.print(printRank(rank, count)));
        printRevenue(statistics, amount);
    }

    private static String printRank(Rank rank, int count) {
        StringBuilder message = new StringBuilder();
        if (!rank.equals(Rank.LOSER)) {
            message.append(String.format(CORRESPOND_FORMAT_MESSAGE, rank.getCount()));
            message.append(printBonusNumber(rank));
            message.append(String.format(PRICE_COUNT_FORMAT_MESSAGE, rank.getPrice(), count));
        }
        return message.toString();
    }

    private static String printBonusNumber(Rank rank) {
        if (rank.isSecond()) {
            return BONUS_BALL_CORRESPOND_MESSAGE;
        }
        return BONUS_BALL_NOT_CORRESPOND_MESSAGE;
    }

    private static void printRevenue(LottoStatistics statistics, int amount) {
        System.out.printf(REVENUE_FORMAT_MESSAGE, statistics.findRevenue(amount));
    }
}
