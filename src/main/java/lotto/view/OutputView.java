package lotto.view;

import java.util.stream.Collectors;

import lotto.domain.lotto.Lotto;
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

    public static void printLottoCount(int amount) {
        System.out.printf("%d개를 구매했습니다.%n", amount);
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
            if (value == LottoRanking.Fail) {
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
