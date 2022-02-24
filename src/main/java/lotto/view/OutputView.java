package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoWinningNumbers;
import lotto.domain.Lottos;
import lotto.domain.Rank;

public class OutputView {

    private static final String SUFFIX_LOTTO_COUNT = "개를 구매했습니다.";
    private static final String EXPLAIN_WINNING_STATISTICS = "당첨 통계";
    private static final String BASIC_LINE = "---------";
    private static final String SUFFIX_SAME_NUMBER = "개 일치 (";
    private static final String SUFFIX_SAME_BONUS_NUMBER = "개 일치, 보너스 볼 일치 (";
    private static final String SUFFIX_WON = "원) - ";
    private static final String SUFFIX_COUNT = "개";
    private static final String SUFFIX_LAST_LINE = "입니다.";
    private static final String PREFIX_PROFIT = "총 수익률은 ";
    private static final String DEFAULT_FORMAT = "%.2f";
    public static final String PRINT_PROFIT = "(기준이 1이기 때문에 결과적으로 이익이라는 의미임)";
    public static final String PRINT_LOSS = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";

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
        printBasicWinningResult(Rank.FIFTH, lottoWinningNumbers.getRankCount(Rank.FIFTH));
        printBasicWinningResult(Rank.FOURTH, lottoWinningNumbers.getRankCount(Rank.FOURTH));
        printBasicWinningResult(Rank.THIRD, lottoWinningNumbers.getRankCount(Rank.THIRD));
        printSecondResult(Rank.SECOND, lottoWinningNumbers.getRankCount(Rank.SECOND));
        printBasicWinningResult(Rank.FIRST, lottoWinningNumbers.getRankCount(Rank.FIRST));
    }

    private static void printBasicWinningResult(final Rank rank, final int count) {
        System.out.println(rank.getMatchCount() + SUFFIX_SAME_NUMBER + rank.getMoney() + SUFFIX_WON + count + SUFFIX_COUNT);
    }

    private static void printSecondResult(final Rank rank, final int count) {
        System.out.println(rank.getMatchCount() + SUFFIX_SAME_BONUS_NUMBER + rank.getMoney() + SUFFIX_WON + count + SUFFIX_COUNT);
    }

    public static void printProfit(final double profit) {
        System.out.print(PREFIX_PROFIT + Double.parseDouble(String.format(DEFAULT_FORMAT, profit)) + SUFFIX_LAST_LINE);
    }

    public static void printWinningLottoProfit() {
        System.out.println(PRINT_PROFIT);
    }

    public static void printWinningLottoLoss() {
        System.out.println(PRINT_LOSS);
    }
}
