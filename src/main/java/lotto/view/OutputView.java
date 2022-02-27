package lotto.view;

import static java.util.stream.Collectors.joining;

import java.util.List;

public class OutputView {


    private OutputView() {

    }

    public static void printLottoSize(int size) {
        System.out.printf("%d개를 구매했습니다.\n", size);
    }

    public static void printLotto(List<Integer> numbers) {
        System.out.println(formattedLottoText(numbers));
    }

    private static String formattedLottoText(List<Integer> numbers) {
        return numbers.stream()
            .sorted()
            .map(String::valueOf)
            .collect(joining(", ", "[", "]"));
    }

    public static void printStatistic(int firstCount, int secondCount, int thirdCount,
        int fourthCount, int fifthCount, double profitRate) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.printf("3개 일치 (5000원)- %d개\n", fifthCount);
        System.out.printf("4개 일치 (50000원)- %d개\n", fourthCount);
        System.out.printf("5개 일치 (1500000원)- %d개\n", thirdCount);
        System.out.printf("5개 일치, 보너스 볼 일치(30000000원)- %d개\n", secondCount);
        System.out.printf("6개 일치 (2000000000원)- %d개\n", firstCount);
        System.out.printf("총 수익률은 %.2f 입니다.(기준이 1이기 떄문에 결과적으로 %s라는 의미임)\n",
            profitRate, summaryWord(profitRate));
    }

    private static String summaryWord(double rate) {
        if (rate < 1.0) {
            return "손해";
        } else if (rate > 1.0) {
            return "이익";
        }
        return "본전";
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
