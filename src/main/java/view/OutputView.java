package view;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import model.LottoRank;

public class OutputView {

    private static final int LOSS = -1;
    private static final int PRINCIPAL = 1;

    public static void printIssuedLottoNumbers(List<Set<Integer>> issuedLottoNumbers) {
        System.out.println(issuedLottoNumbers.size() + "개를 구매했습니다.");
        for (Set<Integer> numbers : issuedLottoNumbers) {
            printEachLottoNumbers(numbers);
        }
    }

    private static void printEachLottoNumbers(Set<Integer> lottoNumbers) {
        String lottoNumbersText = lottoNumbers.stream()
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println(lottoNumbersText);
    }

    public static void printResult(Map<LottoRank, Integer> results, BigDecimal profitRate) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println("3개 일치 (5000원)- " + results.get(LottoRank.FIFTH) + "개");
        System.out.println("4개 일치 (50000원)- " + results.get(LottoRank.FOURTH) + "개");
        System.out.println("5개 일치 (1500000원)- " + results.get(LottoRank.THIRD) + "개");
        System.out.println(
                "5개 일치, 보너스 볼 일치(30000000원)- " + results.get(LottoRank.SECOND) + "개");
        System.out.println("6개 일치 (2000000000원)- " + results.get(LottoRank.FIRST) + "개");
        System.out.println("총 수익률은 " + profitRate
                + "입니다.(기준이 1이기 때문에 결과적으로 " + getSummaryWord(profitRate) + "라는 의미임)");
    }

    private static String getSummaryWord(BigDecimal profitRate) {
        if (isProfitRateLoss(profitRate)) {
            return "손해";
        }
        return "이익";
    }

    private static boolean isProfitRateLoss(BigDecimal profitRate) {
        return profitRate.compareTo(new BigDecimal(PRINCIPAL)) == LOSS;
    }

    public static void printErrorMessage(Exception e) {
        System.out.println("[ERROR]" + ExceptionHandler.getExceptionMessage(e));
    }
}
