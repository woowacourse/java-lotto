package lotto.view;

import static java.util.stream.Collectors.joining;
import static lotto.model.Rank.FIFTH;
import static lotto.model.Rank.FIRST;
import static lotto.model.Rank.FOURTH;
import static lotto.model.Rank.SECOND;
import static lotto.model.Rank.THIRD;

import java.util.List;
import java.util.Map;
import lotto.model.Lotto;
import lotto.model.ProfitRate;
import lotto.model.Statistic;
import lotto.model.exception.DuplicatedNumberException;
import lotto.model.exception.InvalidLottoSizeException;
import lotto.model.exception.InvalidNumberRangeException;
import lotto.model.exception.InvalidRankException;

public class OutputView {

    private OutputView() {

    }

    private static final Map<Class<? extends Exception>, String> EXCEPTION_MESSAGE_MAP =
        Map.of(DuplicatedNumberException.class, "중복된 로또 번호는 입력할 수 없습니다.",
            InvalidLottoSizeException.class, "로또 번호 갯수는 6개여야 합니다.",
            InvalidRankException.class, "일치하는 로또 번호 갯수는 0 ~ 6 사이여야 합니다.",
            InvalidNumberRangeException.class, "로또 번호는 1 ~ 45 사이여야 합니다.");

    public static void printLottoes(List<Lotto> lottoNumbersList) {
        System.out.printf("%d개를 구매했습니다.\n", lottoNumbersList.size());
        for (Lotto numbers : lottoNumbersList) {
            printEachLotto(numbers);
        }
    }

    private static void printEachLotto(Lotto numbers) {
        System.out.println(formattedLottoText(numbers.getIntValues()));
    }

    private static String formattedLottoText(List<Integer> numbers) {
        return numbers.stream()
            .sorted()
            .map(String::valueOf)
            .collect(joining(", ", "[", "]"));
    }

    public static void printStatistic(Statistic result) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.printf("3개 일치 (5000원)- %d개\n", result.getCountByRank(FIFTH));
        System.out.printf("4개 일치 (50000원)- %d개\n", result.getCountByRank(FOURTH));
        System.out.printf("5개 일치 (1500000원)- %d개\n", result.getCountByRank(THIRD));
        System.out.printf("5개 일치, 보너스 볼 일치(30000000원)- %d개\n", result.getCountByRank(SECOND));
        System.out.printf("6개 일치 (2000000000원)- %d개\n",result.getCountByRank(FIRST));
        System.out.printf("총 수익률은 %.2f 입니다.(기준이 1이기 떄문에 결과적으로 %s라는 의미임)\n",
            result.getProfitRate().getDoubleValue(), getSummaryWord(result));
    }

    private static String getSummaryWord(Statistic result) {
        ProfitRate profitRate = result.getProfitRate();
        if (profitRate.isLoss()) {
            return "손해";
        } else if (profitRate.isProfit()) {
            return "이익";
        }
        return "본전";
    }

    public static void printErrorMessage(Exception e) {
        printMessage(errorMessage(e));
    }

    private static String errorMessage(Exception e) {
        if (EXCEPTION_MESSAGE_MAP.containsKey(e.getClass())) {
            return EXCEPTION_MESSAGE_MAP.get(e.getClass());
        }
        return e.getMessage();
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
