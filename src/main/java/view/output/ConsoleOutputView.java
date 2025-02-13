package view.output;

import constans.OutputMessage;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import model.LottoRank;

public class ConsoleOutputView implements OutputView {

    @Override
    public void printPurchaseQuantity(final int purchaseQuantity) {
        System.out.printf(OutputMessage.LOTTO_PURCHASE_COUNT.toString(), purchaseQuantity);
    }

    @Override
    public void printLottoNumbers(List<List<Integer>> lottoNumbers) {
        lottoNumbers.forEach(System.out::println);
    }

    @Override
    public void printLottoStatistics(final double revenueRate, final Map<LottoRank, Integer> lottoRanks,
                                     final boolean isDamage) {
        System.out.println(OutputMessage.LOTTO_WINNING_RESULT_TITLE);

        Arrays.stream(LottoRank.values())
                .filter(rank -> rank != LottoRank.FAIL)
                .forEach(rank -> {
                    String bonusBallMessage = "";
                    if (rank.isBonusBallMatch()) {
                        bonusBallMessage = OutputMessage.LOTTO_WINNING_RESULT_BONUS_BALL.toString();
                    }
                    System.out.printf(OutputMessage.LOTTO_WINNING_RESULT_MATCH.toString(), rank.getMatchCount(),
                            rank.getPrizeMoney(), bonusBallMessage, lottoRanks.getOrDefault(rank, 0));
                });

        String revenueDescription = OutputMessage.LOTTO_REVENUE_PROFIT.toString();
        if (isDamage) {
            revenueDescription = OutputMessage.LOTTO_REVENUE_DAMAGE.toString();
        }
        System.out.printf(OutputMessage.LOTTO_REVENUE.toString(), revenueRate, revenueDescription);
    }
}
