package lotto.view;

import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Number;
import lotto.domain.Rank;
import lotto.domain.Result;

public class OutputView {

    private static final String ERROR_MESSAGE = "[ERROR] : %s%n";
    private static final String LOTTO_COUNT_MESSAGE = "%n수동으로 %d장, 자동으로 %d개를 구매했습니다.%n";
    private static final String LOTTO_MESSAGE_FORMAT = "[%s]%n";
    private static final String MATCH_RESULT_MESSAGE_PREFIX = "%n당첨 통계%n---------%n";
    private static final String NO_BONUS_BALL = " ";
    private static final String BONUS_BALL_MESSAGE = ", 보너스 볼 일치";
    private static final String MATCH_RESULT_MESSAGE = "%d개 일치%s(%d원) - %d개%n";
    private static final String LOSS_SUFFIX = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)%n";
    private static final String PROFIT_SUFFIX = "(와!! 돈복사 버그!!!)%n";
    private static final String SAME_SUFFIX = "(본전... 이득도 손해도 아님)%n";
    private static final String RATE_OF_PROFIT_MESSAGE_PREFIX = "총 수익률은 %.3f입니다.";
    private static final String MANUAL_NUMBERS_MESSAGE = "%n수동으로 구매할 번호를 입력해 주세요.%n";

    private OutputView() {
    }

    public static void printError(String errorMessage) {
        System.out.printf(ERROR_MESSAGE, errorMessage);
    }

    public static void printInitResult(Lottos manualLottos, Lottos autoLottos) {
        printLottoCount(manualLottos.getCount(), autoLottos.getCount());
        printAllLotto(manualLottos, autoLottos);
    }

    private static void printLottoCount(int quantityOfManual, int quantityOfAuto) {
        System.out.printf(LOTTO_COUNT_MESSAGE, quantityOfManual, quantityOfAuto);
    }

    private static void printAllLotto(Lottos... args) {
        for (Lottos lottos : args) {
            printLottos(lottos);
        }
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

        for (Rank rank : Rank.values()) {
            String bonusBallMessage = getBonusBallMessage(rank);

            System.out.printf(MATCH_RESULT_MESSAGE,
                    rank.getCount(), bonusBallMessage, rank.getPrice(), result.getCount(rank));
        }
    }

    private static String getBonusBallMessage(Rank rank) {
        String bonusBallMessage = NO_BONUS_BALL;
        if (rank.isContainBonus()) {
            bonusBallMessage = BONUS_BALL_MESSAGE;
        }

        return bonusBallMessage;
    }

    private static void printRateOfProfit(Result result, Money money) {
        final long totalProfit = result.getTotalProfit();
        final double rateOfProfit = money.calculateRateOfProfit(totalProfit);
        String suffix = LOSS_SUFFIX;

        if (rateOfProfit == 1) {
            suffix = SAME_SUFFIX;
        }
        if (rateOfProfit > 1) {
            suffix = PROFIT_SUFFIX;
        }

        System.out.printf(RATE_OF_PROFIT_MESSAGE_PREFIX + suffix, rateOfProfit);
    }

    public static void printManualNumbersMessage() {
        System.out.printf(MANUAL_NUMBERS_MESSAGE);
    }
}
