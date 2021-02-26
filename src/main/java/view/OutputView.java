package view;

import domain.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String RESULT_FORMAT = "%d개 일치 (%d원)- %d개" + System.lineSeparator();

    private OutputView() {
    }

    public static void printNumberOfPurchaseLotto(int numberOfLotto) {
        System.out.println(numberOfLotto + "개를 구매했습니다.");
    }

    public static void printAllLottos(Lottos lottos) {
        List<Lotto> lottosValues = lottos.getLottos();
        for (Lotto lotto : lottosValues) {
            printLotto(lotto);
        }
    }

    private static void printLotto(Lotto lotto) {
        printLottoNumbers(lotto.toList());
    }

    private static void printLottoNumbers(List<LottoNumber> lottoNumbers) {
        List<Integer> lotto = lottoNumbers.stream()
                .map(LottoNumber::getValue)
                .collect(Collectors.toList());
        System.out.println(lotto);
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
        System.out.printf(RESULT_FORMAT, lottoRank.getCorrectCnt(),
                lottoRank.getPrize().getValue(), resultsValues.get(lottoRank));
    }

    public static void printEarningRate(double earningRate) {
        System.out.printf("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)", earningRate);
    }
}
