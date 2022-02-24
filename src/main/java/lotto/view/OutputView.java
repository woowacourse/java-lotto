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
    private static final String LOTTO_COUNT_MESSAGE = "%d개를 구매했습니다.%n";
    private static final String LOTTO_MESSAGE_FORMAT = "[%s]%n";
    private static final String MATCH_RESULT_MESSAGE_PREFIX = "%n당첨 통계%n---------%n";
    private static final String NO_BONUS_BALL = " ";
    private static final String BONUS_BALL_MESSAGE = ", 보너스 볼 일치";
    private static final String MATCH_RESULT_MESSAGE = "%d개 일치%s(%d원) - %d개%n";
    private static final String LOSS_SUFFIX = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)%n";
    private static final String PROFIT_SUFFIX = "(와!! 돈복사 버그!!!)%n";
    private static final String SAME_SUFFIX = "(본전... 이득도 손해도 아님)%n";
    private static final String RATE_OF_PROFIT_MESSAGE_PREFIX = "총 수익률은 %.3f입니다.";

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
        System.out.printf(LOTTO_COUNT_MESSAGE, lottos.getCount());
    }

    private static void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            String join = lotto.getNumbers().stream()
                    .map(Number::getValue)
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));

            System.out.printf(LOTTO_MESSAGE_FORMAT, join);
        }
    }

    public static void printPlayResult(Result result, Money money) {
        printMatchResult(result);
        printRateOfProfit(result, money);
    }

    private static void printMatchResult(Result result) {
        System.out.printf(MATCH_RESULT_MESSAGE_PREFIX);
        for (WinningPrice value : WinningPrice.values()) {
            if (value == WinningPrice.Fail) {
                continue;
            }
            String bonusBallMessage = NO_BONUS_BALL;
            if (value.isContainBonus()) {
                bonusBallMessage = BONUS_BALL_MESSAGE;
            }
            System.out.printf(MATCH_RESULT_MESSAGE,
                    value.getCount(), bonusBallMessage, value.getPrice(), result.getCount(value));
        }
    }

    private static void printRateOfProfit(Result result, Money money) {
        double rateOfProfit = result.getRateOfProfit(money);
        String suffix = LOSS_SUFFIX;

        if (rateOfProfit == 1) {
            suffix = SAME_SUFFIX;
        }
        if (rateOfProfit > 1) {
            suffix = PROFIT_SUFFIX;
        }

        System.out.printf(RATE_OF_PROFIT_MESSAGE_PREFIX + suffix, rateOfProfit);
    }
}
