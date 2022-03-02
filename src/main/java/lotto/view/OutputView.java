package lotto.view;

import java.util.Arrays;
import lotto.domain.User;
import lotto.domain.Rank;
import lotto.domain.RankStatistic;

public class OutputView {

    private static final String TOTAL_LOTTO_COUNT_MESSAGE_FORMAT = "수동으로 %d장, 자동으로 %d개를 구매했습니다.\n";
    private static final String EACH_LOTTO_MESSAGE_FORMAT = "[%s]\n";
    private static final String WINNING_STATISTIC_TITLE = "당첨 통계\n" + "---------";
    private static final String RANK_MESSAGE_FORMAT = "%d개 일치%s(%d원) - %d개\n";
    private static final String BONUS_NUMBER_MATCH_MESSAGE = ", 보너스 볼 일치";
    private static final String BONUS_NUMBER_MISMATCH_MESSAGE = " ";
    private static final String PROFIT_RATE_MASSAGE_FORMAT = "총 수익률은 %s입니다.\n";
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR]";

    public static void printLottos(User user) {
        printNewLine();
        System.out.printf(TOTAL_LOTTO_COUNT_MESSAGE_FORMAT,
                user.getCountOfManualLotto(),
                user.getCountOfAutoLotto());
        user.getLottos().forEach(lotto -> System.out.printf(EACH_LOTTO_MESSAGE_FORMAT, lotto.toString()));
    }

    public static void printLottoResult(User user, RankStatistic rankStatistic) {
        printNewLine();
        printWinningStatistic(rankStatistic);
        printProfitRate(user, rankStatistic);
    }

    private static void printWinningStatistic(RankStatistic rankStatistic) {
        System.out.println(WINNING_STATISTIC_TITLE);
        Arrays.stream(Rank.values())
                .filter(rank -> !rank.equals(Rank.RANK_OUT))
                .forEach(
                        rank -> System.out.printf((RANK_MESSAGE_FORMAT),
                                rank.getWinningLottoMatchCount(),
                                getBonusNumberMessage(rank),
                                rank.getPrize(),
                                rankStatistic.getCount(rank))
                );
    }

    private static String getBonusNumberMessage(Rank rank) {
        if (rank.isBonusNumberMatch()) {
            return BONUS_NUMBER_MATCH_MESSAGE;
        }
        return BONUS_NUMBER_MISMATCH_MESSAGE;
    }

    private static void printProfitRate(User user, RankStatistic rankStatistic) {
        double profitRate = user.calculateProfitRate(rankStatistic.getTotalPrize());
        System.out.printf((PROFIT_RATE_MASSAGE_FORMAT), toStringProfitRateUntilSecondDecimal(profitRate));
    }

    public static String toStringProfitRateUntilSecondDecimal(double profitRate) {
        return String.valueOf(Math.floor(profitRate * 100) / 100.0);
    }

    public static void printErrorMessage(Exception exception) {
        System.out.println(ERROR_MESSAGE_PREFIX + exception.getMessage());
    }

    public static void printNewLine() {
        System.out.println();
    }
}
