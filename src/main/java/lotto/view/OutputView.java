package lotto.view;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lotto.dto.RankResult;
import lotto.dto.StatisticsResult;

public class OutputView {

    private static final List<String> RANKS = List.of("FIFTH", "FOURTH", "THIRD", "SECOND", "FIRST");
    private static final String SECOND_RANK = "SECOND";

    private static final String LOTTO_NUMBER_DELIMITER = ", ";

    public static void printLottos(List<List<Integer>> lottos) {
        for (List<Integer> lotto : lottos) {
            Collections.sort(lotto);
            System.out.println(MessageFormat.format("[{0}]", joinWithDelimiter(lotto)));
        }
    }

    public static void printLottosSize(int manualLottoAmount, int autoLottoAmount) {
        System.out.println(MessageFormat.format(
            "수동으로 {0}장, 자동으로 {1}개를 구매했습니다.", manualLottoAmount, autoLottoAmount));
    }

    public static void printRanks(StatisticsResult statisticsResult) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        for (String rank : RANKS) {
            printRank(rank, statisticsResult.getStatistics().get(rank));
        }
        System.out.printf("총 수익률은 %.2f 입니다.", statisticsResult.getProfitRate());
    }

    private static void printRank(String rank, RankResult statistics) {
        if (rank.equals(SECOND_RANK)) {
            printSecondRank(statistics.getMatchCount(), statistics.getReward(), statistics.getRewardCount());
            return;
        }
        printOtherRank(statistics.getMatchCount(), statistics.getReward(), statistics.getRewardCount());
    }

    private static String joinWithDelimiter(List<Integer> lottoNumbers) {
        return lottoNumbers.stream()
            .map(String::valueOf)
            .collect(Collectors.joining(LOTTO_NUMBER_DELIMITER));
    }

    private static void printSecondRank(int matchCount, long reward, int rewardCount) {
        System.out.printf("%d개 일치, 보너스 볼 일치(%d원) - %d개%n", matchCount, reward, rewardCount);
    }

    private static PrintStream printOtherRank(int matchCount, long reward, int rewardCount) {
        return System.out.printf("%d개 일치 (%d원)- %d개%n", matchCount, reward, rewardCount);
    }
}
