package lotto.view;

import static lotto.common.Constants.LINE_SEPARATOR;

import java.util.List;
import java.util.stream.IntStream;

public class OutputView {

    private static final List<String> CORRECT_MESSAGE = List.of("", "3개 일치 (5000원)- ", "4개 일치 (50000원)- ",
            "5개 일치 (1500000원)- ", "5개 일치, 보너스 볼 일치(30000000원)- ", "6개 일치 (2000000000원)- ");

    private OutputView() {
    }

    public static void printError(Exception e) {
        System.out.println("[ERROR] " + e.getMessage());
    }

    public static void printLottoGroup(int getTicketCount, String lottoGroup) {
        System.out.println(getTicketCount + "개를 구매했습니다.");
        System.out.println(lottoGroup);
        System.out.println();
    }

    public static void printStatics(List<Integer> correctCountValues, String profitRate) {
        printNoticeResultMessage();
        IntStream.range(1, CORRECT_MESSAGE.size())
                .forEach(index -> System.out.println(CORRECT_MESSAGE.get(index) + correctCountValues.get(index) + "개"));

        printProfitRate(profitRate);
    }

    private static void printNoticeResultMessage() {
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    private static void printProfitRate(String profitRate) {
        System.out.printf(LINE_SEPARATOR + "총 수익률은 %s입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)", profitRate);
    }
}

