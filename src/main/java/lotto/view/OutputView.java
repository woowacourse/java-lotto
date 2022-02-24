package lotto.view;

import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.domain.RankCount;

public class OutputView {

    private static final String TOTAL_LOTTO_COUNT_MESSAGE = "개를 구매했습니다.";
    private static final String WINNING_STATISTIC_TITLE = "당첨 통계\n" + "---------";

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printTotalLottoCount(int totalLottoCount) {
        System.out.println(totalLottoCount + TOTAL_LOTTO_COUNT_MESSAGE);
    }

    public static void printLottos(Lottos lottos) {
        lottos.getLottos().forEach(System.out::println);
    }

    public static void printWinningStatistic(RankCount rankCount) {
        System.out.println(WINNING_STATISTIC_TITLE);
        Rank[] values = Rank.values();
        for (int i = 0; i < 5; i++) {
            Rank rank = values[i];
            printRank(rankCount, rank);
        }
    }

    private static void printRank(RankCount rankCount, Rank rank) {
        if (rank.isBonusNumberMatch()) {
            System.out.println(
                    rank.isBonusNumberMatch() + "개 일치, 보너스 볼 일치(" + rank.getPrizeToString() + "원) - " + rankCount.getCount(rank) + "개");
            return;
        }
        System.out.println(
                rank.isBonusNumberMatch() + "개 일치 (" + rank.getPrizeToString() + "원) - " + rankCount.getCount(rank) + "개");
    }

    public static void printNewLine() {
        System.out.println();
    }
}
