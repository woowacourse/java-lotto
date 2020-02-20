package lotto.view;

import lotto.domain.number.AllLottoNumbers;
import lotto.domain.number.LottoNumber;
import lotto.domain.number.LottoNumbers;
import lotto.domain.number.PurchaseNumber;
import lotto.domain.result.GameResult;
import lotto.domain.result.GameResults;
import lotto.domain.result.Yield;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String PURCHASE_NUMBER_POSTFIX = "개를 구입했습니다.";

    public static void printPurchaseNumber(PurchaseNumber purchaseNumber) {
        System.out.println(purchaseNumber.getPurchaseNumber() + PURCHASE_NUMBER_POSTFIX);
    }

    public static void printAllLottoNumbers(AllLottoNumbers allLottoNumbers) {
        List<LottoNumbers> allLottoNumbersList = allLottoNumbers.getAllLottoNumbers();
        for (int i = 0; i < allLottoNumbersList.size(); i++) {
            printLottoNumbers(allLottoNumbersList.get(i));
        }
    }

    private static void printLottoNumbers(LottoNumbers lottoNumbers) {
        List<LottoNumber> lottoNumberGroups = lottoNumbers.getLottoNumbers();
        String output = lottoNumberGroups.stream()
                .map(LottoNumber::getNumber)
                .map(Object::toString)
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println(output);
    }

    public static void printGameResults(GameResults results) {
        System.out.println("당첨 통계\n" + "---------");
        Arrays.stream(GameResult.values())
                .filter(gameResult -> !gameResult.equals(GameResult.NO_RANK))
                .forEach(gameResult -> printGameResultElement(results, gameResult));
    }

    private static void printGameResultElement(GameResults results, GameResult gameResult) {
        StringBuilder sb = new StringBuilder();
        sb.append(gameResult.getCorrectLottoNumberSize());
        sb.append("개 일치");
        if (gameResult.equals(GameResult.SECOND_RANK)) {
            sb.append("보너스 볼 일치");
        }
        sb.append("(").append(gameResult.getPrize()).append("원)-");
        sb.append(results.calculateCaseNumberSize(gameResult)).append("개");
        System.out.println(sb.toString());
    }

    public static void printYield(Yield yield) {
        BigDecimal bigDecimal = new BigDecimal(Math.round(yield.getYield()));
        System.out.println("총 수익률은 " + bigDecimal.toString() + "% 입니다.");
    }
}
