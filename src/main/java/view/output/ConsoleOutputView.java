package view.output;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import model.LottoRank;

public class ConsoleOutputView implements OutputView {

    private static final String LOTTO_PURCHASE_COUNT_MESSAGE = "%d개를 구매했습니다.\n";
    private static final String LOTTO_WINNING_RESULT_TITLE_MESSAGE = "\n당첨 통계\n---------";
    private static final String LOTTO_WINNING_RESULT_MATCH_MESSAGE = "%d개 일치%s(%d원)- %d개\n";
    private static final String LOTTO_WINNING_RESULT_BONUS_BALL_MESSAGE = ", 보너스 볼 일치";
    private static final String LOTTO_REVENUE_MESSAGE = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)\n";
    private static final String LOTTO_REVENUE_DAMAGE_MESSAGE = "손해";
    private static final String LOTTO_REVENUE_PROFIT_MESSAGE = "이익";

    @Override
    public void printPurchaseQuantity(final int purchaseQuantity) {
        System.out.printf(LOTTO_PURCHASE_COUNT_MESSAGE, purchaseQuantity);
    }

    @Override
    public void printLottoNumbers(final List<List<Integer>> lottoNumbers) {
        lottoNumbers.forEach(System.out::println);
    }

    @Override
    public void printLottoStatistics(
        final double revenueRate,
        final Map<LottoRank, Integer> lottoRanks,
        final boolean isDamage
    ) {
        System.out.println(LOTTO_WINNING_RESULT_TITLE_MESSAGE);
        Arrays.stream(LottoRank.values())
            .filter(rank -> rank != LottoRank.FAIL)
            .forEach(rank -> printLottoResult(rank, lottoRanks));
        printLottoRevenue(revenueRate, isDamage);
    }

    private void printLottoResult(
        final LottoRank rank,
        final Map<LottoRank, Integer> resultRanks
    ) {
        String bonusBallMessage = " ";
        if (rank.isBonusBallMatch()) {
            bonusBallMessage = LOTTO_WINNING_RESULT_BONUS_BALL_MESSAGE;
        }
        System.out.printf(LOTTO_WINNING_RESULT_MATCH_MESSAGE, rank.getMatchCount(), bonusBallMessage,
            rank.getPrizeMoney(), resultRanks.getOrDefault(rank, 0));
    }

    private void printLottoRevenue(
        final double revenueRate,
        final boolean isDamage
    ) {
        String revenueDescription = LOTTO_REVENUE_PROFIT_MESSAGE;
        if (isDamage) {
            revenueDescription = LOTTO_REVENUE_DAMAGE_MESSAGE;
        }
        System.out.printf(LOTTO_REVENUE_MESSAGE, revenueRate, revenueDescription);
    }

    @Override
    public void printErrorMessage(final String errorMessage) {
        System.out.println(errorMessage);
    }
}
