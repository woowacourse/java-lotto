package lotto.view;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.dto.Result;

public class OutputView {

    private static final String PREFIX_MANUAL_LOTTO_AMOUNT = "수동으로 ";
    private static final String SUFFIX_MANUAL_LOTTO_AMOUNT = "장, ";
    private static final String PREFIX_AUTOMATIC_LOTTO_AMOUNT = "자동으로 ";
    private static final String SUFFIX_AUTOMATIC_LOTTO_AMOUNT = "개를 구매했습니다.";
    private static final String EXPLAIN_WINNING_STATISTICS = "당첨 통계";
    private static final String BASIC_LINE = "---------";
    private static final String SUFFIX_SAME_NUMBER = "개 일치 (";
    private static final String SUFFIX_SAME_BONUS_NUMBER = "개 일치, 보너스 볼 일치 (";
    private static final String SUFFIX_WON = "원) - ";
    private static final String SUFFIX_COUNT = "개";
    private static final String SUFFIX_LAST_LINE = "입니다.";
    private static final String PREFIX_PROFIT = "총 수익률은 ";
    private static final String DEFAULT_FORMAT = "%.2f";
    public static final String PREFIX_PRINT_LOTTO = "[";
    public static final String SUFFIX_PRINT_LOTTO = "]";
    public static final String PRINT_LOTTO_DELIMITER = ", ";

    public static void printLottos(final int manualLottosAmount, final Lottos lottos) {
        System.out.println();
        if (manualLottosAmount != 0) {
            System.out.print(PREFIX_MANUAL_LOTTO_AMOUNT + manualLottosAmount + SUFFIX_MANUAL_LOTTO_AMOUNT);
        }
        System.out.println(PREFIX_AUTOMATIC_LOTTO_AMOUNT + (lottos.getSize() - manualLottosAmount) + SUFFIX_AUTOMATIC_LOTTO_AMOUNT);
        lottos.getLottos()
                .stream()
                .forEach(lotto -> printLotto(lotto));
        System.out.println();
    }

    private static void printLotto(final Lotto lotto) {
        System.out.print(PREFIX_PRINT_LOTTO);
        int size = lotto.getNumbers().size();
        for (int i = 0; i < size - 1; i++) {
            System.out.print(lotto.getNumbers().get(i).getLottoNumber() + PRINT_LOTTO_DELIMITER);
        }
        System.out.print(lotto.getNumbers().get(size - 1).getLottoNumber());
        System.out.println(SUFFIX_PRINT_LOTTO);
    }

    public static void printWinningResult(final Result reuslt) {
        System.out.println();
        System.out.println(EXPLAIN_WINNING_STATISTICS);
        System.out.println(BASIC_LINE);
        List<Rank> ranks = Arrays.asList(Rank.values()).subList(0, Rank.values().length - 1);
        Collections.reverse(ranks);
        ranks.stream()
                .forEach(rank -> printRankResult(rank, reuslt.getRankCount(rank)));
    }

    private static void printRankResult(final Rank rank, final int count) {
        if (rank == Rank.SECOND) {
            System.out.println(rank.getMatchCount() + SUFFIX_SAME_BONUS_NUMBER + rank.getMoney() + SUFFIX_WON + count + SUFFIX_COUNT);
            return;
        }
        System.out.println(rank.getMatchCount() + SUFFIX_SAME_NUMBER + rank.getMoney() + SUFFIX_WON + count + SUFFIX_COUNT);
    }

    public static void printProfit(final double profit) {
        System.out.print(PREFIX_PROFIT + Double.parseDouble(String.format(DEFAULT_FORMAT, profit)) + SUFFIX_LAST_LINE);
    }
}
