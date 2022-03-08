package view;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import service.dto.LottoDto;
import service.dto.LottoResultDto;
import service.dto.PurchasedLottosDto;

public class OutputView {

    private static final int LOSS = -1;
    private static final int PRINCIPAL = 1;

    public static void printIssuedLottos(PurchasedLottosDto purchasedLotto) {
        System.out.println("수동으로 " + purchasedLotto.getManualCount() + "개, 자동으로 "
                + purchasedLotto.getAutoCount() + "개를 구매했습니다.");
        List<List<Integer>> lottosNumbers = purchasedLotto.getAllLottos().getLottos().stream()
                .map(LottoDto::getLottoNumbers).collect(Collectors.toList());
        for (List<Integer> numbers : lottosNumbers) {
            printEachLottoNumbers(numbers);
        }
    }

    private static void printEachLottoNumbers(List<Integer> lottoNumbers) {
        String lottoNumbersText = lottoNumbers.stream()
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println(lottoNumbersText);
    }

    public static void printResult(LottoResultDto lottoResultDto) {
        Map<String, Integer> results = lottoResultDto.getResult();
        BigDecimal profitRate = lottoResultDto.getProfitRate();
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println("3개 일치 (5000원)- " + results.get("FIFTH") + "개");
        System.out.println("4개 일치 (50000원)- " + results.get("FOURTH") + "개");
        System.out.println("5개 일치 (1500000원)- " + results.get("THIRD") + "개");
        System.out.println(
                "5개 일치, 보너스 볼 일치(30000000원)- " + results.get("SECOND") + "개");
        System.out.println("6개 일치 (2000000000원)- " + results.get("FIRST") + "개");
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
