package lotto.view;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoWinningNumbers;
import lotto.domain.lotto.Lottos;
import lotto.domain.result.LottoResult;
import lotto.domain.result.Rank;

public class OutputView {

    private static final String SUFFIX_LOTTO_COUNT = "개를 구매했습니다.";
    private static final String EXPLAIN_WINNING_STATISTICS = "당첨 통계";
    private static final String BASIC_LINE = "---------";
    private static final String PRINT_WINNING_STATISTIC = "%d개 일치 (%d원) - %d개";
    private static final String PRINT_SECOND_WINNING_STATISTIC = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
    private static final String PRINT_TOTAL_RETURN = "총 수익률은 %.2f입니다.";

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

    public static void printWinningResult(LottoResult result) {
        System.out.println();
        System.out.println(EXPLAIN_WINNING_STATISTICS);
        System.out.println(BASIC_LINE);
        Arrays.stream(Rank.values())
                .sorted(Comparator.reverseOrder())
                        .filter(Predicate.not(rank -> rank == Rank.NO_MATCH))
                                .forEach(rank -> printLottoResult(rank, result));
    }

    private static void printLottoResult(Rank rank, LottoResult result) {
        if (rank == Rank.SECOND){
            System.out.println(String.format(PRINT_SECOND_WINNING_STATISTIC, rank.getMatchCount(), rank.getMoney(), result.getRankCount(rank)));
            return;
        }
        System.out.println(String.format(PRINT_WINNING_STATISTIC, rank.getMatchCount(), rank.getMoney(), result.getRankCount(rank)));
    }

    public static void printProfit(final double profit) {
        System.out.print(String.format(PRINT_TOTAL_RETURN, profit));
    }
}
