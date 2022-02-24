package lotto.view;

import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Number;
import lotto.domain.Result;
import lotto.domain.WinningPrice;

public class OutputView {

    private static final String ERROR_MESSAGE = "[ERROR] : %s%n";

    private OutputView() {
    }

    public static void printError(String errorMessage) {
        System.out.printf(ERROR_MESSAGE, errorMessage);
    }

    public static void printInitResult(Lottos lottos) {
        printLottoCount(lottos);
        printLottos(lottos);
    }

    private static void printLottoCount(Lottos lottos) {
        System.out.printf("%d개를 구매했습니다.%n", lottos.getCount());
    }

    private static void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            String join = lotto.getNumbers().stream()
                    .map(Number::getValue)
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));

            System.out.printf("[%s]%n", join);
        }
    }

    public static void printPlayResult(Result result, Money money) {
        printMatchResult(result);
        printRateOfProfit(result, money);
    }

    private static void printMatchResult(Result result) {
        System.out.printf("%n당첨 통계%n---------%n");
        for (WinningPrice value : WinningPrice.values()) {
            if (value == WinningPrice.Fail) {
                continue;
            }
            String bonusBallMessage = " ";
            if (value.isContainBonus()) {
                bonusBallMessage = ", 보너스 볼 일치";
            }
            System.out.printf("%d개 일치%s(%d원) - %d개%n",
                    value.getCount(), bonusBallMessage, value.getPrice(), result.getCount(value));
        }
    }

    private static void printRateOfProfit(Result result, Money money) {
        double rateOfProfit = result.getRateOfProfit(money);
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
