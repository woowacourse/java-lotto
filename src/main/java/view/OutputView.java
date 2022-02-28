package view;


import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import model.Lotto;
import model.LottoRank;
import model.LottoResult;

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
        String lottoNumbersText = lottoNumbers.stream().sorted().map(String::valueOf)
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
        System.out.println("[ERROR]" + e.getMessage());
    }
}
