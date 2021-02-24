package lotto.view;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.model.LottoRank;
import lotto.model.LottoResults;

public class OutputView {

    public static void printExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public static void printPurchaseNumOfManualLottoGuideMessage() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
    }

    public static void printPurchaseManualLottoGuideMessage() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }

    public static void printPurchaseAmountGuideMessage() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printLottoPurchaseResult(Lottos manualLottos, Lottos automaticLottos) {
        System.out.println();

        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", manualLottos.getNumOfLottos(),
            automaticLottos.getNumOfLottos());
        printLottos(manualLottos);
        printLottos(automaticLottos);
        System.out.println();
    }

    private static void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            List<Integer> lottoValues = lotto.getNumbers()
                .stream()
                .map(number -> number.getValue())
                .collect(Collectors.toList());
            System.out.println(lottoValues.toString());
        }
    }

    public static void printWinningLottoGuideMessage() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public static void printWinningLottoBonusGuideMessage() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public static void printLottoResult(LottoResults lottoResults) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
        List<LottoRank> sortedResultKey = sortedResultKey(lottoResults.toMap().keySet());
        for (int i = 1; i < sortedResultKey.size(); i++) {
            LottoRank lottoRank = sortedResultKey.get(i);
            int correct = lottoRank.getCorrect();
            int prize = lottoRank.getPrize();
            int numOfMatch = lottoResults.findNumOfMatchByKey(lottoRank);
            System.out.println(correct + "개 일치 (" + prize + "원)- " + numOfMatch + "개");
        }
        printLottoEariningRate(lottoResults.getEarningsRate());
    }

    private static List<LottoRank> sortedResultKey(Set<LottoRank> keySet) {
        return keySet.stream()
            .sorted((k1, k2) -> Integer.compare(k1.getPrize(), k2.getPrize()))
            .collect(Collectors.toList());
    }

    private static void printLottoEariningRate(double earningsRate) {
        System.out.printf("총 수익률은 %.2f입니다.", earningsRate);
    }
}
