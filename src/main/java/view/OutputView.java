package view;

import domain.Lotto;
import domain.LottoRank;
import domain.LottoResults;
import domain.Lottos;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

public class OutputView {

    private static final String format = "%d개 일치 (%d원)- %d개" + System.lineSeparator();

    private OutputView() {
    }

    public static void printNumberOfPurchasedLotto(int numberOfLotto) {
        System.out.println(numberOfLotto + "개를 구매했습니다.");
    }

    public static void printAllLottoList(Lottos boughtLottos) {
        for (Lotto lotto : boughtLottos.getLottos()) {
            System.out.println(lotto.getValues());
        }
    }

    public static void printResults(LottoResults results) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        Arrays.stream(LottoRank.values())
                .sorted(Comparator.reverseOrder())
                .filter(lottoRank -> lottoRank != LottoRank.MISS)
                .forEach(lottoRank -> printResult(results.getValues(), lottoRank));
    }

    private static void printResult(Map<LottoRank, Long> resultsValues, LottoRank lottoRank) {
        System.out.printf(format, lottoRank.getMatchCount(), lottoRank.getPrize().getValue(), resultsValues.get(lottoRank));
    }

    public static void printEarningRate(double earningRate) {
        System.out.printf("총 수익률은 %.2f입니다.", earningRate);
    }
}
