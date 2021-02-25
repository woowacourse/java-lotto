package view;

import domain.lottoGame.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

public class OutputView {

    private static final String format = "%d개 일치 (%d원)- %d개" + System.lineSeparator();

    private OutputView() {
    }

    public static void printPurchaseInformation(Lottos boughtLottos) {
        printNumberOfPurchasedLotto(boughtLottos.getNumberOfLotto());
        printAllLottoList(boughtLottos);
    }

    private static void printNumberOfPurchasedLotto(int numberOfLotto) {
        System.out.println(numberOfLotto + "개를 구매했습니다.");
    }

    private static void printAllLottoList(Lottos boughtLottos) {
        for (Lotto lotto : boughtLottos.getLottos()) {
            System.out.println(lotto.getNumbers());
        }
    }

    public static void printResult(LottoResult lottoResult) {
        printWinningTable(lottoResult.getWinningTable());
        printEarningRate(lottoResult.getEarningRate());
    }

    private static void printWinningTable(LottoWinningTable results) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        Arrays.stream(LottoRank.values())
                .sorted(Comparator.reverseOrder())
                .filter(lottoRank -> lottoRank != LottoRank.MISS)
                .forEach(lottoRank -> printResult(results.getValues(), lottoRank));
    }

    private static void printResult(Map<LottoRank, Long> resultsValues, LottoRank lottoRank) {
        System.out.printf(format, lottoRank.getCorrectCount(), lottoRank.getPrize().getValue(), resultsValues.get(lottoRank));
    }

    private static void printEarningRate(double earningRate) {
        System.out.printf("총 수익률은 %.2f입니다.", earningRate);
    }
}
