package lotto.view;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.vo.Money;
import lotto.domain.vo.LottoNumber;
import lotto.domain.Rank;

public class OutputView {

    private static final String DELIMITER = ", ";

    public static void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            List<LottoNumber> lottoNumbers = new ArrayList<>(lotto.getNumbers());
            lottoNumbers.sort(Comparator.comparingInt(LottoNumber::getNumber));
            System.out.println(MessageFormat.format("[{0}]", joinWithDelimiter(lottoNumbers)));
        }
    }

    public static void printLottosSize(int manualLottoAmount, int autoLottoAmount) {
        System.out.println(MessageFormat.format(
            "수동으로 {0}장, 자동으로 {1}개를 구매했습니다.", manualLottoAmount, autoLottoAmount));
    }

    public static void printRanks(List<Rank> ranks) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        for (Rank rank : List.of(Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST)) {
            printRank(ranks, rank);
        }
    }

    public static void printRate(final double totalReward, Money inputMoney) {
        System.out.printf("총 수익률은 %.2f 입니다.", totalReward / inputMoney.getAmount());
    }

    private static void printRank(List<Rank> ranks, Rank rank) {
        int matchCount = rank.getMatchCount();
        long reward = rank.getReward();
        int rewardCount = rank.findRewardCount(ranks);

        if (rank == Rank.SECOND) {
            printSecondRank(matchCount, reward, rewardCount);
            return;
        }
        printOtherRank(matchCount, reward, rewardCount);
    }

    private static String joinWithDelimiter(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
            .map(number -> String.valueOf(number.getNumber()))
            .collect(Collectors.joining(DELIMITER));
    }

    private static void printSecondRank(int matchCount, long reward, int rewardCount) {
        System.out.printf("%d개 일치, 보너스 볼 일치(%d원) - %d개%n", matchCount, reward, rewardCount);
    }

    private static PrintStream printOtherRank(int matchCount, long reward, int rewardCount) {
        return System.out.printf("%d개 일치 (%d원)- %d개%n", matchCount, reward, rewardCount);
    }
}
