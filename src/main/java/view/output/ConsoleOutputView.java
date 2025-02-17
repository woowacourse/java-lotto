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
                                     final boolean isRevenue) {
        System.out.println("\n당첨 통계\n---------");
        printLottoRanks(lottoRanks);
        printLottoRevenue(revenueRate, isRevenue);
    }

    private void printLottoRanks(final Map<LottoRank, Integer> lottoRanks) {
        Arrays.stream(LottoRank.values())
                .filter(rank -> rank != LottoRank.FAIL)
                .forEach(rank -> printLottoResult(rank, lottoRanks));
    }

    private void printLottoResult(final LottoRank rank, final Map<LottoRank, Integer> resultRanks) {
        final String bonusBallMessage = selectByFlag(rank.isBonusBallMatch(), ", 보너스 볼 일치", " ");
        System.out.printf("%d개 일치%s(%d원)- %d개\n", rank.getMatchCount(), bonusBallMessage,
                rank.getPrizeMoney(), resultRanks.getOrDefault(rank, 0));
    }

    private void printLottoRevenue(final double revenueRate, final boolean isRevenue) {
        final String revenueDescription = selectByFlag(isRevenue, "이익이", "손해");
        System.out.printf("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)\n", revenueRate, revenueDescription);
    }

    private String selectByFlag(final boolean selectLeft, final String left, final String right) {
        if (selectLeft) {
            return left;
        }
        return right;
    }

    @Override
    public void printErrorMessage(final String errorMessage) {
        System.out.println(errorMessage);
    }
}
