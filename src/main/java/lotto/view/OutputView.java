package lotto.view;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Reward;

public class OutputView {

    private static final String BUY_LOTTO_COUNT_MESSAGE = "수동으로 %d장, 자동으로 %d개를 구매했습니다.";
    private static final String DELIMITER = ", ";
    private static final String DELIMITER_HEAD = "[";
    private static final String DELIMITER_TAIL = "]";
    private static final String WINNING_STATISTICS = "당첨 통계";
    private static final String CONTOUR = "---------";
    private static final String MATCHES_MESSAGE = "%d개 일치 (%d원)- %d";
    private static final String FIVE_MATCHES_WITH_BONUS_NUMBER = "%d개 일치, 보너스 볼 일치 (%d원)- %d";
    private static final String TOTAL_PROFIT = "총 수익률은 %.2f 입니다.";
    private static final String FORMAT_STRING = "%s%s%s";
    private static final String BUY_NUMBERS_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String LOTTO_NUMBERS_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";

    public static void printBuyLottoCountMessage(final int selfCount, final int autoCount) {
        System.out.printf((BUY_LOTTO_COUNT_MESSAGE) + "%n", selfCount, autoCount);
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

    public static void printMatchMessage(final Reward reward, int count) {
        System.out.printf((MATCHES_MESSAGE) + "%n", reward.getHitCount(), reward.getWinningMoney(),
            count);
    }

    public static void printMatchBonusMessage(final Reward reward, int count) {
        System.out.printf((FIVE_MATCHES_WITH_BONUS_NUMBER) + "%n", reward.getHitCount(),
            reward.getWinningMoney(), count);
    }

    public static void printProfitMessage(double profit) {
        System.out.printf((TOTAL_PROFIT) + "%n", profit);
    }

    public static void printBuyLottoNumberMessage() {
        System.out.println(BUY_NUMBERS_MESSAGE);
    }

    public static void printWinningLottoMessage() {
        System.out.println(LOTTO_NUMBERS_MESSAGE);
    }
}