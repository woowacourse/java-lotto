package view;

import exception.DuplicatedLottoNumbersException;
import exception.InvalidLottoNumbersSizeException;
import exception.InvalidMatchCountException;
import exception.InvalidRangeLottoNumberException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.LottoNumbers;
import model.LottoRank;
import model.LottoResult;

public class OutputView {

    private static final int LOSS = -1;
    private static final int PRINCIPAL = 1;
    private static final Map<Class<? extends Exception>, String> EXCEPTION_MESSAGE_MAP =
        Map.of(DuplicatedLottoNumbersException.class, "중복된 로또 번호는 입력할 수 없습니다.",
            InvalidLottoNumbersSizeException.class, "로또 번호 갯수는 6개여야 합니다.",
            InvalidMatchCountException.class, "일치하는 로또 번호 갯수는 0 ~ 6 사이여야 합니다.",
            InvalidRangeLottoNumberException.class, "로또 번호는 1 ~ 45 사이여야 합니다.");

    public static void printIssuedLottoNumbers(List<LottoNumbers> lottoNumbersList) {
        System.out.println(lottoNumbersList.size() + "개를 구매했습니다.");
        for (LottoNumbers numbers : lottoNumbersList) {
            printEachLottoNumbers(numbers);
        }
    }

    private static void printEachLottoNumbers(LottoNumbers numbers) {
        String lottoNumbersText = numbers.getIntValues().stream().sorted().map(String::valueOf)
            .collect(Collectors.joining(", ", "[", "]"));
        System.out.println(lottoNumbersText);
    }

    public static void printResult(LottoResult result) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println("3개 일치 (5000원)- " + result.getCountByRank(LottoRank.FIFTH) + "개");
        System.out.println("4개 일치 (50000원)- " + result.getCountByRank(LottoRank.FOURTH) + "개");
        System.out.println("5개 일치 (1500000원)- " + result.getCountByRank(LottoRank.THIRD) + "개");
        System.out.println(
            "5개 일치, 보너스 볼 일치(30000000원)- " + result.getCountByRank(LottoRank.SECOND) + "개");
        System.out.println("6개 일치 (2000000000원)- " + result.getCountByRank(LottoRank.FIRST) + "개");
        System.out.println("총 수익률은 " + result.getProfitRate()
            + "입니다.(기준이 1이기 때문에 결과적으로 " + getSummaryWord(result) + "라는 의미임)");
    }

    private static String getSummaryWord(LottoResult result) {
        if (isProfitRateLoss(result)) {
            return "손해";
        }
        return "이익";
    }

    private static boolean isProfitRateLoss(LottoResult result) {
        return result.getProfitRate().compareTo(new BigDecimal(PRINCIPAL)) == LOSS;
    }

    public static void printErrorMessage(Exception e) {
        System.out.println("[ERROR]" + getErrorMessage(e));
    }

    private static String getErrorMessage(Exception e) {
        if (EXCEPTION_MESSAGE_MAP.containsKey(e.getClass())) {
            return EXCEPTION_MESSAGE_MAP.get(e.getClass());
        }
        return e.getMessage();
    }
}
