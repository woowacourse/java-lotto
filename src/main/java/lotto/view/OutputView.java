package lotto.view;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.vo.LottoNumber;

public class OutputView {

    private static final String DELIMITER = ", ";

    public static void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            List<LottoNumber> lottoNumbers = new ArrayList<>(lotto.getNumbers());
            lottoNumbers.sort(Comparator.comparingInt(LottoNumber::getNumber));
            System.out.println(MessageFormat.format("[{0}]", joinWithDelimiter(lottoNumbers)));
        }
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
