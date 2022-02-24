package lotto.view;

import static java.util.stream.Collectors.joining;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import lotto.model.Lotto;
import lotto.model.ProfitRate;
import lotto.model.Rank;
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
        System.out.println(lottoNumbersList.size() + "개를 구매했습니다.");
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
        System.out.println("3개 일치 (5000원)- " + result.getCountByRank(Rank.FIFTH) + "개");
        System.out.println("4개 일치 (50000원)- " + result.getCountByRank(Rank.FOURTH) + "개");
        System.out.println("5개 일치 (1500000원)- " + result.getCountByRank(Rank.THIRD) + "개");
        System.out.println(
            "5개 일치, 보너스 볼 일치(30000000원)- " + result.getCountByRank(Rank.SECOND) + "개");
        System.out.println("6개 일치 (2000000000원)- " + result.getCountByRank(Rank.FIRST) + "개");
        System.out.println("총 수익률은 " + result.getProfitRate().getDoubleValue()
            + "입니다.(기준이 1이기 때문에 결과적으로 " + getSummaryWord(result) + "라는 의미임)");
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
