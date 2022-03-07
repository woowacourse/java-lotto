package lotto.view;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.Rank;

public class OutputView {

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

    public static void printRanks(Map<Rank, Integer> result) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        for (Rank rank : List.of(Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST)) {
            printRank(result, rank);
        }
    }

    public static void printRate(double rate) {
        System.out.printf("총 수익률은 %.2f 입니다.", rate);
    }

    private static void printRank(Map<Rank, Integer> result, Rank rank) {
        if (rank == Rank.SECOND) {
            printSecondRank(rank.getMatchCount(), rank.getReward(), result.get(rank));
            return;
        }
        printOtherRank(rank.getMatchCount(), rank.getReward(), result.get(rank));
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
