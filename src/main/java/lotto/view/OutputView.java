package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.RankBoard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String LOTTO_JOIN_DELIMITER = ", ";

    private OutputView() {
    }

    public static void printLottos(int manualLottoCount, int autoLottoCount, List<Lotto> lottos) {
        printLottoCount(manualLottoCount, autoLottoCount);

        for (Lotto lotto : lottos) {
            System.out.println(makeLottoString(lotto));
        }
        System.out.println();
    }

    private static void printLottoCount(int manualLottoCount, int autoLottoCount) {
        System.out.println("수동으로 " + manualLottoCount + "개, 자동으로 " + autoLottoCount + "개를 구매했습니다.");
    }

    private static String makeLottoString(Lotto lotto) {
        String result = lotto.getIntValues().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(LOTTO_JOIN_DELIMITER));
        return "[" + result + "]";
    }

    public static void printResult(RankBoard rankBoard, double profitRatio) {
        printResultTitle();
        printRankCounter(rankBoard);
        printProfitRatio(profitRatio);
    }

    private static void printResultTitle() {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    private static void printRankCounter(RankBoard rankBoard) {
        ArrayList<Rank> ranks = new ArrayList<>(Arrays.asList(Rank.values()));
        ranks.sort(Comparator.comparing(Rank::getPrize));
        for (Rank rank : ranks) {
            printRank(rank, rankBoard);
        }
    }

    private static void printRank(Rank rank, RankBoard rankBoard) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(rank.getMatched()).append("개 일치");
        if (rank == Rank.SECOND) {
            stringBuilder.append(", 보너스 볼 일치");
        }
        stringBuilder.append(" (").append(rank.getPrize()).append("원) - ").append(rankBoard.getCount(rank)).append("개");

        System.out.println(stringBuilder);
    }

    private static void printProfitRatio(double profitRatio) {
        System.out.println("총 수익률은 " + profitRatio + "입니다.");
    }
}
