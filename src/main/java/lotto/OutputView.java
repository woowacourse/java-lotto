package lotto;

import java.text.MessageFormat;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DELIMITER = ",";

    public static void printLottos(List<Lotto> lottos) {
        printLottosSize(lottos);
        printLottoNumbers(lottos);
    }

    public static void printRanks(List<Rank> ranks) {
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    public static void printRate(Rate rate) {
        System.out.println(MessageFormat.format("총 수익률은 {0}입니다.", rate.getRate().toString()));
    }

    private static void printLottosSize(List<Lotto> lottos) {
        System.out.println(MessageFormat.format("{0}개를 구매했습니다.", lottos.size()));
    }

    private static void printLottoNumbers(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            List<Number> numbers = lotto.getNumbers();
            numbers.sort(Comparator.comparingInt(Number::getNumber));

            System.out.println(MessageFormat.format("[{0}]", joinWithDelimiter(numbers)));
        }
    }

    private static String joinWithDelimiter(List<Number> numbers) {
        return numbers.stream()
            .map(number -> String.valueOf(number.getNumber()))
            .collect(Collectors.joining(DELIMITER));
    }
}
