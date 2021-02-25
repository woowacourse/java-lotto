package view;

import domain.lottoGame.*;
import domain.lottoGame.dto.LottoResult;
import domain.lottoGame.dto.PurchaseResult;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

public class OutputView {

    private OutputView() {
    }

    public static void printPurchaseResult(PurchaseResult result) {
        printNumberOfPurchasedLotto(result.getNumberOfManualPurchase(), result.getNumberOfRandomPurchase());
        printLottos(result.getPurchasedLottos());
    }

    private static void printNumberOfPurchasedLotto(int numberOfManual, int numberOfRandom) {
        String format = "수동으로 %d장, 자동으로 %d장 구매했습니다." + System.lineSeparator();
        System.out.printf(format, numberOfManual, numberOfRandom);
    }

    private static void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.getNumbers());
        }
    }

    public static void printLottoResults(LottoResult result) {
        printWinningTable(result.getWinningTable());
        printEarningRate(result.getEarningRate());
    }

    private static void printWinningTable(LottoWinningTable winningTable) {
        System.out.println("당첨 통계" + System.lineSeparator() + "---------");

        Arrays.stream(LottoRank.values())
                .sorted(Comparator.reverseOrder())
                .filter(lottoRank -> lottoRank != LottoRank.MISS)
                .forEach(lottoRank -> printWinningResult(winningTable.getValues(), lottoRank));
    }

    private static void printWinningResult(Map<LottoRank, Long> resultsValues, LottoRank rank) {
        String format = "%d개 일치 (%d원)- %d개" + System.lineSeparator();
        System.out.printf(format, rank.getCorrectCount(), rank.getPrize().getValue(), resultsValues.get(rank));
    }

    private static void printEarningRate(double earningRate) {
        System.out.printf("총 수익률은 %.2f입니다.", earningRate);
    }
}
