package lotto.view;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.result.LottoResult;
import lotto.domain.result.Rank;

public class OutputView {

    public static final String PRINT_MANUAL_NUMBER = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String SUFFIX_LOTTO_COUNT = "수동으로 %d장, 자동으로 %d개를 구매했습니다.";
    private static final String EXPLAIN_WINNING_STATISTICS = "당첨 통계";
    private static final String BASIC_LINE = "---------";
    private static final String PRINT_WINNING_STATISTIC = "%d개 일치 (%d원) - %d개";
    private static final String PRINT_SECOND_WINNING_STATISTIC = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
    private static final String PRINT_TOTAL_RETURN = "총 수익률은 %.2f입니다.";

    public static void printLottos(final Lottos lottos, int purchaseLottoCount, int remainPurchaseLottoCount) {
        System.out.println();
        System.out.println(String.format(SUFFIX_LOTTO_COUNT, purchaseLottoCount, remainPurchaseLottoCount));
        for (Lotto lotto : lottos.getLottos()) {
            printLotto(lotto);
        }
        System.out.println();
    }

    private static void printLotto(final Lotto lotto) {
        System.out.println(lotto.getNumbers().toString());
    }

    public static void printWinningResult(final LottoResult result) {
        System.out.println();
        System.out.println(EXPLAIN_WINNING_STATISTICS);
        System.out.println(BASIC_LINE);
        Arrays.stream(Rank.values())
                .sorted(Comparator.reverseOrder())
                .filter(Predicate.not(rank -> rank == Rank.NO_MATCH))
                .forEach(rank -> printLottoResult(rank, result));
    }

    private static void printLottoResult(final Rank rank, final LottoResult result) {
        if (rank == Rank.SECOND) {
            System.out.println(String.format(PRINT_SECOND_WINNING_STATISTIC, rank.getMatchCount(), rank.getMoney(),
                    result.getRankCount(rank)));
            return;
        }
        System.out.println(String.format(PRINT_WINNING_STATISTIC, rank.getMatchCount(), rank.getMoney(),
                result.getRankCount(rank)));
    }

    public static void printProfit(final double profit) {
        System.out.print(String.format(PRINT_TOTAL_RETURN, profit));
    }

    public static void printErrorMessage(String value) {
        System.out.println(value);
    }

    public static void printManualLotto() {
        System.out.println();
        System.out.println(PRINT_MANUAL_NUMBER);
    }
}
