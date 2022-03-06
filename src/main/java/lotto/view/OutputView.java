package lotto.view;

import java.util.Arrays;
import lotto.domain.Lotto;
import lotto.domain.LottoCounter;
import lotto.domain.Lottos;
import lotto.domain.LottoPurchaseMoney;
import lotto.domain.Rank;
import lotto.domain.RankCounter;

public class OutputView {

    private static final String TOTAL_LOTTO_COUNT_MESSAGE = "수동으로 %d장, 자동으로 %d개를 구매했습니다.\n";
    private static final String WINNING_STATISTIC_TITLE = "당첨 통계\n" + "---------";
    private static final String RANK_MESSAGE_FORMAT = "%s개 일치%s(%s원) - %d개\n";
    private static final String BONUS_NUMBER_MATCH_MESSAGE = ", 보너스 볼 일치";
    private static final String BONUS_NUMBER_MISMATCH_MESSAGE = " ";
    private static final String PROFIT_RATE_MASSAGE_FORMAT = "총 수익률은 %.2f입니다.\n";

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printLottos(LottoCounter lottoCounter, Lottos lottos) {
        printNewLine();
        System.out.printf(TOTAL_LOTTO_COUNT_MESSAGE,
                lottoCounter.getManualLottoCount(), lottoCounter.getAutoLottoCount());
        lottos.getLottos().stream()
                .map(Lotto::getLottoToInteger)
                .forEach(System.out::println);
    }

    public static void printWinningStatistic(LottoPurchaseMoney lottoPurchaseMoney, RankCounter rankCounter) {
        printNewLine();
        System.out.println(WINNING_STATISTIC_TITLE);
        Arrays.stream(Rank.values())
                .filter(rank -> !rank.equals(Rank.RANK_OUT))
                .forEach(rank -> System.out.printf((RANK_MESSAGE_FORMAT),
                        rank.toStringWinningNumberCount(), getBonusNumberMessage(rank),
                        rank.toStringPrize(), rankCounter.getCountOfRank(rank)));
        System.out.printf((PROFIT_RATE_MASSAGE_FORMAT), lottoPurchaseMoney.calculateProfitRate(rankCounter.getTotalPrize()));
    }

    private static String getBonusNumberMessage(Rank rank) {
        if (rank.isMatchBonusNumber()) {
            return BONUS_NUMBER_MATCH_MESSAGE;
        }
        return BONUS_NUMBER_MISMATCH_MESSAGE;
    }

    public static void printNewLine() {
        System.out.println();
    }
}
