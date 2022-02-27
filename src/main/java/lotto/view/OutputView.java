package lotto.view;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;
import lotto.domain.Lotto;
import lotto.domain.LottoWinningNumbers;
import lotto.domain.Lottos;
import lotto.domain.Rank;

public class OutputView {

    private static final String SUFFIX_LOTTO_COUNT = "개를 구매했습니다.";
    private static final String EXPLAIN_WINNING_STATISTICS = "당첨 통계";
    private static final String BASIC_LINE = "---------";
    private static final String PRINT_WINNING_STATISTIC = "%d개 일치 (%d원)- %d개";
    private static final String PRINT_SECOND_WINNING_STATISTIC = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
    private static final String PRINT_TOTAL_RETURN = "총 수익률은 %.2f입니다.";
    private static final String PRINT_PROFIT = "(기준이 1이기 때문에 결과적으로 이익이라는 의미임)";
    private static final String PRINT_LOSS = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";

    public static void printLottos(final Lottos lottos) {
        System.out.println(lottos.getLottos().size() + SUFFIX_LOTTO_COUNT);
        for (Lotto lotto : lottos.getLottos()) {
            printLotto(lotto);
        }
        System.out.println();
    }

    private static void printLotto(final Lotto lotto) {
        System.out.println(lotto.getNumbers().toString());
    }

    public static void printWinningResult(final LottoWinningNumbers lottoWinningNumbers) {
        System.out.println();
        System.out.println(EXPLAIN_WINNING_STATISTICS);
        System.out.println(BASIC_LINE);
        Arrays.stream(Rank.values())
                .sorted(Comparator.reverseOrder())
                        .filter(Predicate.not(rank -> rank == Rank.NO_MATCH))
                                .forEach(rank -> printLottoResult(rank, lottoWinningNumbers));
    }

    private static void printLottoResult(Rank rank, LottoWinningNumbers lottoWinningNumbers) {
        if (rank == Rank.SECOND){
            System.out.println(String.format(PRINT_SECOND_WINNING_STATISTIC, rank.getMatchCount(), rank.getMoney(), lottoWinningNumbers.getRankCount(rank)));
        }
        System.out.println(String.format(PRINT_WINNING_STATISTIC, rank.getMatchCount(), rank.getMoney(), lottoWinningNumbers.getRankCount(rank)));
    }

    public static void printProfit(final double profit) {
        System.out.print(String.format(PRINT_TOTAL_RETURN, profit));
    }

    public static void printWinningLottoProfit() {
        System.out.println(PRINT_PROFIT);
    }

    public static void printWinningLottoLoss() {
        System.out.println(PRINT_LOSS);
    }
}
