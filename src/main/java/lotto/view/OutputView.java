package lotto.view;


import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.model.LottoRank;
import lotto.model.LottoResult;

public class OutputView {

    public static void printExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public static void printPurchaseAmountGuideMessage() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printLottos(Lottos lottos) {
        System.out.println(lottos.getNumOfLottos() + "개를 구매했습니다.");
        for (Lotto lotto : lottos.getLottos()) {
            List<Integer> lottoValues = lotto.getNumbers()
                .stream()
                .map(number -> number.getValue())
                .collect(Collectors.toList());
            System.out.println(lottoValues.toString());
        }
        System.out.println();
    }

    public static void printWinningLottoGuideMessage() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public static void printWinningLottoBonusGuideMessage() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public static void printLottoResult(LottoResult lottoResult) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        Object[] keys = lottoResult.getResult().keySet().toArray();
        for (Entry<LottoRank, Integer> entry : lottoResult.getResult().entrySet()) {
            int correct = entry.getKey().getCorrect();
            int prize = entry.getKey().getPrize();
            int numOfMatch = entry.getValue();
            System.out.println(correct + "개 일치 (" + prize + "원)- " + numOfMatch + "개");
        }
        printLottoEariningRate(lottoResult.getEarningsRate());
    }

    private static void printLottoEariningRate(double earningsRate) {
        System.out.printf("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)", earningsRate);
    }
}
