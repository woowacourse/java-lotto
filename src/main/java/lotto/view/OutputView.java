package lotto.view;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Reword;
import lotto.domain.Rewords;

public class OutputView {

    private static final String BUY_LOTTO_COUNT_MESSAGE = "%s개를 구매했습니다.";
    private static final String DELIMITER = ", ";
    private static final String DELIMITER_HEAD = "[";
    private static final String DELIMITER_TAIL = "]";
    private static final String WINNING_STATISTICS = "당첨 통계";
    private static final String CONTOUR = "---------";
    private static final String MATCHES_MESSAGE = "%d개 일치 (%d원)- %d";
    private static final String FIVE_MATCHES_WITH_BONUS_NUMBER = "%d개 일치, 보너스 볼 일치 (%d원)- %d";
    private static final String TOTAL_PROFIT = "총 수익률은 %.2f 입니다.";
    public static final String FORMAT_STRING = "%s%s%s";

    public static void printBuyLottoCountMessage(final int value) {
        System.out.printf((BUY_LOTTO_COUNT_MESSAGE) + "%n", value);
    }

    public static void printLottoMessage(List<Integer> values) {
        String numbers = values.stream()
            .map(String::valueOf)
            .collect(Collectors.joining(DELIMITER));

        System.out.printf((FORMAT_STRING) + "%n", DELIMITER_HEAD, numbers, DELIMITER_TAIL);
    }

    public static void printNewLineMessage() {
        System.out.println();
    }

    public static void printResultMessage() {
        System.out.println(WINNING_STATISTICS);
        System.out.println(CONTOUR);
    }

    public static void printMatchMessage(final Reword reword, int count) {
        System.out.printf((MATCHES_MESSAGE) + "%n", reword.getHitCount(), reword.getWinningMoney(),
            count);
    }

    public static void printMatchBonusMessage(final Reword reword, int count) {
        System.out.printf((FIVE_MATCHES_WITH_BONUS_NUMBER) + "%n", reword.getHitCount(),
            reword.getWinningMoney(), count);
    }

    public static void printProfitMessage(double profit) {
        System.out.printf((TOTAL_PROFIT) + "%n", profit);
    }
}