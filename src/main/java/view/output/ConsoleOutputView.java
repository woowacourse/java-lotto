package view.output;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import model.LottoRank;

public class ConsoleOutputView implements OutputView {

    @Override
    public void printPurchaseQuantity(final int purchaseQuantity) {
        System.out.printf("%d개를 구매했습니다.\n", purchaseQuantity);
    }

    @Override
    public void printLottoNumbers(final List<List<Integer>> lottoNumbers) {
        lottoNumbers.forEach(System.out::println);
    }

    @Override
    public void printLottoStatistics(final double revenueRate, final Map<LottoRank, Integer> lottoRanks,
                                     final boolean isDamage) {
        System.out.println("\n당첨 통계\n---------");
        Arrays.stream(LottoRank.values())
                .filter(rank -> rank != LottoRank.FAIL)
                .forEach(rank -> printLottoResult(rank, lottoRanks));
        printLottoRevenue(revenueRate, isDamage);
    }

    private void printLottoResult(final LottoRank rank, final Map<LottoRank, Integer> resultRanks) {
        final String bonusBallMessage = selectByFlag(rank.isBonusBallMatch(), ", 보너스 볼 일치", " ");
        System.out.printf("%d개 일치%s(%d원)- %d개\n", rank.getMatchCount(), bonusBallMessage,
                rank.getPrizeMoney(), resultRanks.getOrDefault(rank, 0));
    }

    private void printLottoRevenue(final double revenueRate, final boolean isDamage) {
        final String revenueDescription = selectByFlag(isDamage, "손해", "이익이");
        System.out.printf("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)\n", revenueRate, revenueDescription);
    }

    private String selectByFlag(final boolean flag, final String trueValue, final String falseValue) {
        if (flag) {
            return trueValue;
        }
        return falseValue;
    }

    @Override
    public void printErrorMessage(final String errorMessage) {
        System.out.println(errorMessage);
    }
}
