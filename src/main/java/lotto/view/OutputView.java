package lotto.view;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.vo.Number;
import lotto.domain.Rank;
import lotto.controller.Rate;

public class OutputView {

    private static final String DELIMITER = ",";

    public static void printLottos(List<Lotto> lottos) {
        printLottosSize(lottos);
        printLottoNumbers(lottos);
    }

    private static void printLottosSize(List<Lotto> lottos) {
        System.out.println(MessageFormat.format("{0}개를 구매했습니다.", lottos.size()));
    }

    private static void printLottoNumbers(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            List<Number> numbers = lotto.getNumbers();

            System.out.println(MessageFormat.format("[{0}]", joinWithDelimiter(numbers)));
        }
    }

    private static String joinWithDelimiter(List<Number> numbers) {
        return numbers.stream()
                .map(number -> String.valueOf(number.getNumber()))
                .collect(Collectors.joining(DELIMITER));
    }

    public static void printRanks(List<Rank> ranks) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        for (Rank rank : orderedRanks()) {
            printRank(ranks, rank);
        }
    }

    private static List<Rank> orderedRanks() {
        return List.of(Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST);
    }

    private static void printRank(List<Rank> ranks, Rank rank) {
        if (rank == Rank.SECOND) {
            printSecondRank(ranks, rank);
            return;
        }
        printOtherRank(ranks, rank);
    }

    private static void printSecondRank(List<Rank> ranks, Rank rank) {
        int matchCount = rank.getMatchCount();
        long reward = rank.getReward().getMoney();
        int rewardCount = findRewardCount(rank, ranks);
        System.out.printf("%d개 일치, 보너스 볼 일치(%d원) - %d개%n", matchCount, reward, rewardCount);
    }

    private static void printOtherRank(List<Rank> ranks, Rank rank) {
        int matchCount = rank.getMatchCount();
        long reward = rank.getReward().getMoney();
        int rewardCount = findRewardCount(rank, ranks);
        System.out.printf("%d개 일치 (%d원)- %d개%n", matchCount, reward, rewardCount);
    }

    private static int findRewardCount(Rank rank, List<Rank> ranks) {
        return rank.findRewardCount(ranks);
    }

    public static void printRate(Rate rate) {
        System.out.println(MessageFormat.format("총 수익률은 {0}입니다.", rate.getRate().toString()));
    }
}
