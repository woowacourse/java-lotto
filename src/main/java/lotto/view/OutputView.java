package lotto.view;

import static java.util.stream.Collectors.joining;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

public class OutputView {

    private final PrintStream printStream;

    public OutputView(OutputStream outputStream) {
        printStream = new PrintStream(outputStream);
    }

    public void printLottoSize(int size) {
        printStream.printf("%d개를 구매했습니다.\n", size);
    }

    public void printLotto(List<Integer> numbers) {
        printStream.println(formattedLottoText(numbers));
    }

    private String formattedLottoText(List<Integer> numbers) {
        return numbers.stream()
            .sorted()
            .map(String::valueOf)
            .collect(joining(", ", "[", "]"));
    }

    public void printStatistic(int firstCount, int secondCount, int thirdCount,
        int fourthCount, int fifthCount, double profitRate) {
        printStream.println("당첨 통계");
        printStream.println("---------");
        printStream.printf("3개 일치 (5000원)- %d개\n", fifthCount);
        printStream.printf("4개 일치 (50000원)- %d개\n", fourthCount);
        printStream.printf("5개 일치 (1500000원)- %d개\n", thirdCount);
        printStream.printf("5개 일치, 보너스 볼 일치(30000000원)- %d개\n", secondCount);
        printStream.printf("6개 일치 (2000000000원)- %d개\n", firstCount);
        printStream.printf("총 수익률은 %.2f 입니다.(기준이 1이기 떄문에 결과적으로 %s라는 의미임)\n",
            profitRate, summaryWord(profitRate));
    }

    private String summaryWord(double rate) {
        if (rate < 1.0) {
            return "손해";
        } else if (rate > 1.0) {
            return "이익";
        }
        return "본전";
    }

    public void printMessage(String message) {
        printStream.println(message);
    }
}
