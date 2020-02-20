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
    private static final String PREFIX = "[";
    private static final String SUFFIX = "]";
    private static final String GAME_RESULTS_HEADER = "당첨 통계\n" + "---------";
    private static final String CORRECT_BONUS_BALL_MESSAGE = "보너스 볼 일치";

    public static void printPurchaseNumber(PurchaseNumber purchaseNumber) {
        System.out.println(purchaseNumber.getPurchaseNumber() + PURCHASE_NUMBER_POSTFIX);
    }

    public static void printAllLottoNumbers(AllLottoNumbers allLottoNumbers) {
        List<LottoNumbers> allLottoNumbersList = allLottoNumbers.getAllLottoNumbers();
        for (LottoNumbers anAllLottoNumbersList : allLottoNumbersList) {
            printLottoNumbers(anAllLottoNumbersList);
        }
    }

    private static void printLottoNumbers(LottoNumbers lottoNumbers) {
        List<LottoNumber> lottoNumberGroups = lottoNumbers.getLottoNumbers();
        String output = lottoNumberGroups.stream()
                .map(LottoNumber::getNumber)
                .map(Object::toString)
                .collect(Collectors.joining(InputView.DELIMITER, PREFIX, SUFFIX));
        System.out.println(output);
    }

    public static void printGameResults(GameResults results) {
        System.out.println(GAME_RESULTS_HEADER);
        Arrays.stream(GameResult.values())
                .filter(gameResult -> !gameResult.equals(GameResult.NO_RANK))
                .forEach(gameResult -> printGameResultElement(results, gameResult));
    }

    private static void printGameResultElement(GameResults results, GameResult gameResult) {
        StringBuilder sb = new StringBuilder();
        sb.append(gameResult.getCorrectLottoNumberSize())
                .append("개 일치");
        ifCorrectBonusNumberAddBonusNumberString(gameResult, sb);
        sb.append("(")
                .append(new BigDecimal(gameResult.getPrize()).toString())
                .append("원)-")
                .append(results.calculateCaseNumberSize(gameResult))
                .append("개");
        System.out.println(sb.toString());
    }

    private static void ifCorrectBonusNumberAddBonusNumberString(GameResult gameResult, StringBuilder sb) {
        if (gameResult.equals(GameResult.SECOND_RANK)) {
            sb.append(CORRECT_BONUS_BALL_MESSAGE);
        }
    }

    public static void printYield(Yield yield) {
        BigDecimal bigDecimal = new BigDecimal(Math.round(yield.getYield()));
        System.out.println("총 수익률은 " + bigDecimal.toString() + "% 입니다.");
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}
