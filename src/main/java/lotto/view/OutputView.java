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
    private static final int DECIMAL_PLACE = 2;

    public static void printLottos(List<Lotto> lottos) {
        printLottosSize(lottos);
        printLottoNumbers(lottos);
    }

    public static void printRanks(List<Rank> ranks) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        for (Rank rank : List.of(Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST)) {
            printRank(ranks, rank);
        }
    }

    public static void printRate(Money totalReward, Money inputMoney) {
        System.out.println(MessageFormat.format("총 수익률은 {0}입니다.", measureRatio(totalReward, inputMoney)));
    }

    private static BigDecimal measureRatio(Money totalReward, Money inputMoney) {
        return totalReward.divide(inputMoney, DECIMAL_PLACE, RoundingMode.DOWN);
    }

    private static void printLottosSize(List<Lotto> lottos) {
        System.out.println(MessageFormat.format("{0}개를 구매했습니다.", lottos.size()));
    }

    private static void printLottoNumbers(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            List<LottoNumber> lottoNumbers = new ArrayList<>(lotto.getNumbers());
            lottoNumbers.sort(Comparator.comparingInt(LottoNumber::getNumber));
            System.out.println(MessageFormat.format("[{0}]", joinWithDelimiter(lottoNumbers)));
        }
    }

    private static void printRank(List<Rank> ranks, Rank rank) {
        int matchCount = rank.getMatchCount();
        long reward = rank.getReward().getAmount();
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
