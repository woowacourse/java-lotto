package lotto.view;

import lotto.domain.LottoGame;
import lotto.domain.number.LottoNumber;
import lotto.domain.number.LottoRound;
import lotto.domain.number.LottoRounds;
import lotto.domain.result.GameResult;
import lotto.domain.result.GameResults;
import lotto.domain.result.Money;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OutputView {
    private static final String PURCHASE_NUMBER_POSTFIX = "개를 구입했습니다.";
    private static final String PREFIX = "[";
    private static final String SUFFIX = "]";
    private static final String GAME_RESULTS_HEADER = "당첨 통계\n" + "---------";
    private static final String PURCHASE_NUMBER_NOTICE = "수동으로 %d장, 자동으로 %d개를 구매했습니다.";
    private static final String PRINT_YIELD_HEADER = "총 수익률은 ";
    private static final String PRINT_YIELD_FOOTER = "% 입니다.";

    public static void printPurchaseNumber(Money money, List<LottoRound> manualLottos) {
        int manualLottoSize = manualLottos.size();
        System.out.printf(PURCHASE_NUMBER_NOTICE
                , manualLottos.size()
                , money.calculateRound() - manualLottoSize);
        System.out.println(money.calculateRound() + PURCHASE_NUMBER_POSTFIX);
    }

    public static void printAllLottoNumbers(LottoGame lottoGame) {
        LottoRounds lottoRounds = lottoGame.getLottoRounds();
        List<LottoRound> allLottoRoundList = lottoRounds.getAllLottoNumbers();
        for (LottoRound anAllLottoRoundList : allLottoRoundList) {
            printLottoNumbers(anAllLottoRoundList);
        }
    }

    private static void printLottoNumbers(LottoRound lottoRound) {
        Set<LottoNumber> lottoNumberGroups = lottoRound.getLottoNumbers();
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
        String gameResultNotice = "%d개 일치 (%s원) - %d개\n";
        if (gameResult.equals(GameResult.SECOND_RANK)) {
            gameResultNotice = "%d개 일치, 보너스 볼 일치(%s원) - %d개\n";
        }
        int correctLottoNumberSize = gameResult.getCorrectLottoNumberSize();
        String prize = new BigDecimal(gameResult.getPrize()).toString();
        int gameResultSize = results.calculateCaseNumberSize(gameResult);
        System.out.printf(gameResultNotice, correctLottoNumberSize, prize, gameResultSize);
    }

    public static void printYield(double yield) {
        BigDecimal bigDecimal = new BigDecimal(Math.round(yield));
        System.out.println(PRINT_YIELD_HEADER + bigDecimal.toString() + PRINT_YIELD_FOOTER);
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}
