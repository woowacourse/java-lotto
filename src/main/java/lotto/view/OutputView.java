package lotto.view;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Rewords;

public class OutputView {

    private static final String BUY_LOTTO_COUNT_MESSAGE = "%s개를 구매했습니다.";
    private static final String INPUT_MONEY_MESSAGE = "구매금액을 입력해 주세요.";
    private static final String DELIMITER = ", ";
    private static final String DELIMITER_HEAD = "[";
    private static final String DELIMITER_TAIL = "]";
    private static final String LOTTO_NUMBERS_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String WINNING_STATISTICS = "당첨 통계";
    private static final String CONTOUR = "---------";
    private static final String THREE_MATCHES = "3개 일치 (5000원)- %d";
    private static final String FIVE_MATCHES = "5개 일치 (1500000원)- %d";
    private static final String FIVE_MATCHES_WITH_BONUS_NUMBER = "5개 일치, 보너스 볼 일치 (30000000원)- %d";
    private static final String SIX_MATCHES = "6개 일치 (2000000000원)- %d";
    private static final String TOTAL_PROFIT = "총 수익률은 %.2f 입니다.";
    private static final String FOUR_MATCHES = "4개 일치 (50000원)- %d";
    public static final String FORMAT_STRING = "%s%s%s";
    private static final int FIRST = 1;
    private static final int SECOND = 2;
    private static final int THIRD = 3;
    private static final int FOURTH = 4;
    private static final int FIFTH = 5;

    public static void printInputMoneyMessage() {
        System.out.println(INPUT_MONEY_MESSAGE);
    }

    public static void printBuyLottoCountMessage(final int value) {
        System.out.println(String.format(BUY_LOTTO_COUNT_MESSAGE, value));
    }

    public static void printLottoNumbersMessage() {
        System.out.println(LOTTO_NUMBERS_MESSAGE);
    }

    public static void printInputBonusBallMessage() {
        System.out.println(INPUT_BONUS_BALL_MESSAGE);
    }

    public static void printLottoMessage(List<Integer> values) {
        String numbers = values.stream()
            .map(String::valueOf)
            .collect(Collectors.joining(DELIMITER));

        System.out.println(String.format(FORMAT_STRING, DELIMITER_HEAD, numbers, DELIMITER_TAIL));
    }

    public static void printNewLineMessage() {
        System.out.println();
    }

    public static void printResultMessage(final Rewords rewords, final int money) {
        System.out.println(WINNING_STATISTICS);
        System.out.println(CONTOUR);
        System.out.println(String.format(THREE_MATCHES, rewords.getRankCount(FIRST)));
        System.out.println(String.format(FOUR_MATCHES, rewords.getRankCount(SECOND)));
        System.out.println(String.format(FIVE_MATCHES, rewords.getRankCount(THIRD)));
        System.out.println(String.format(FIVE_MATCHES_WITH_BONUS_NUMBER, rewords.getRankCount(FOURTH)));
        System.out.println(String.format(SIX_MATCHES, rewords.getRankCount(FIFTH)));
        System.out.println(String.format(TOTAL_PROFIT, rewords.profit(money)));
    }
}
