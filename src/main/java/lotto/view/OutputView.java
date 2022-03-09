package lotto.view;

import java.util.stream.Collectors;

import lotto.domain.lotto.Count;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoCountToBuy;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.Number;
import lotto.domain.Result;
import lotto.domain.LottoRanking;

public class OutputView {

    private OutputView() {
    }

    public static void printException(Exception exception) {
        System.out.println(exception.getMessage());
    }

    public static void printRequestManualLottoNumberUI() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.(\", \"를 기준으로 6개 입력해주세요.)");
    }

    public static void printLottoCount(LottoCountToBuy lottoCountToBuy) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.%n",
            lottoCountToBuy.getManualCount(), lottoCountToBuy.getAutoCount());
    }

    public static void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            String join = lotto.getNumbers()
                .stream()
                .map(Number::getValue)
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

            System.out.printf("[%s]%n", join);
        }
    }

    public static void printResult(Result result) {
        System.out.printf("%n당첨 통계%n---------%n");
        for (LottoRanking value : LottoRanking.values()) {
            if (value == LottoRanking.FAIL) {
                continue;
            }
            String bonusBallMessage = " ";
            if (value.isContainsBonus()) {
                bonusBallMessage = ", 보너스 볼 일치";
            }
            System.out.printf("%d개 일치%s(%d원) - %d개%n", value.getCount(), bonusBallMessage, value.getPrice(),
                result.getCount(value));
        }
    }

    public static void printRateOfProfit(double rateOfProfit) {
        String suffix = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
        if (rateOfProfit == 1) {
            suffix = "(본전)";
        }
        if (rateOfProfit > 1) {
            suffix = "(이득)";
        }

        String prefix = "총 수익률은 %.3f입니다." + suffix;
        System.out.printf(prefix + "%n", rateOfProfit);
    }
}
