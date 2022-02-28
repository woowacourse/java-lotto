package lotto.view;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.Lotto;
import lotto.domain.vo.LottoNumber;
import lotto.domain.Rank;
import lotto.controller.Rate;

public class OutputView {

    private static final String DELIMITER = ",";

    private OutputView() {
    }

    public static void printLottoCount(int manualLottoCount, int automaticLottoCount) {
        System.out.println(MessageFormat.format("수동으로 {0}장, 자동으로 {1}개를 구매했습니다.", manualLottoCount, automaticLottoCount));
    }

    public static void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            List<LottoNumber> numbers = lotto.getNumbers();

            System.out.println(MessageFormat.format("[{0}]", joinWithDelimiter(numbers)));
        }
    }

    private static String joinWithDelimiter(List<LottoNumber> numbers) {
        return numbers.stream()
                .map(number -> String.valueOf(number.getNumber()))
                .collect(Collectors.joining(DELIMITER));
    }

    public static void printRanks(List<Rank> ranks) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        for (Rank rank : reverseOrderedRanks()) {
            printRank(ranks, rank);
        }
    }

    private static List<Rank> reverseOrderedRanks() {
        List<Rank> ranks = new ArrayList<>(List.of(Rank.values()));
        ranks.remove(Rank.NONE);
        ranks.sort(Collections.reverseOrder());
        return ranks;
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
        long reward = rank.getReward().getValue();
        int rewardCount = rank.findRewardCount(ranks);
        System.out.printf("%d개 일치, 보너스 볼 일치(%d원) - %d개%n", matchCount, reward, rewardCount);
    }

    private static void printOtherRank(List<Rank> ranks, Rank rank) {
        int matchCount = rank.getMatchCount();
        long reward = rank.getReward().getValue();
        int rewardCount = rank.findRewardCount(ranks);
        System.out.printf("%d개 일치 (%d원)- %d개%n", matchCount, reward, rewardCount);
    }

    public static void printRate(Rate rate) {
        System.out.println(MessageFormat.format("총 수익률은 {0}입니다.", rate.getRate().toString()));
    }
}
