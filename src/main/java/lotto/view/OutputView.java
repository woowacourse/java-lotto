package lotto.view;

import lotto.domain.Rewords;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DELIMITER = ", ";
    private static final String DELIMITER_HEAD = "[";
    private static final String DELIMITER_TAIL = "]";
    private static final String CONTOUR = "---------";

    public static void printInputMoneyMessage() {
        System.out.println("구매금액을 입력해 주세요.");
    }

    public static void printBuyLottoCountMessage(final int value) {
        System.out.printf("%s개를 구매했습니다.%n", value);
    }

    public static void printLottoNumbersMessage() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public static void printInputBonusBallMessage() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public static void printLottoMessage(List<Integer> values) {
        String numbers = values.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(DELIMITER));

        System.out.printf("%s %s %s%n", DELIMITER_HEAD, numbers, DELIMITER_TAIL);
    }

    public static void printNewLineMessage() {
        System.out.println();
    }

    public static void printResultMessage(final Rewords rewords, final int money) {
        System.out.println("당첨 통계");
        System.out.println(CONTOUR);
        System.out.printf("3개 일치 (5000원)- %d%n", rewords.getFifth());
        System.out.printf("4개 일치 (50000원)- %d%n", rewords.getFourth());
        System.out.printf("5개 일치 (1500000원)- %d%n", rewords.getThird());
        System.out.printf("5개 일치, 보너스 볼 일치 (30000000원)- %d%n", rewords.getSecond());
        System.out.printf("6개 일치 (2000000000원)- %d%n", rewords.getFirst());
        System.out.printf("총 수익률은 %.2f 입니다.%n", rewords.profit(money));
    }
}
