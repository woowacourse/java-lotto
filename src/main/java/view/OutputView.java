package view;

import domain.Lotto;
import domain.PrizeResult;
import domain.PurchasedLotto;
import domain.WinnerPrice;

import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String PURCHASE_COUNT_MESSAGE = "개를 구매했습니다.";
    private static final String LEFT_BRACKET = "[";
    private static final String RIGHT_BRACKET = "]";
    private static final String DELIMITER = ", ";
    private static final String WINNING_STATISTIC = "당첨 통계";
    private static final String OUTLINE = "---------";
    private static final String STATISTICS_PRIZE_MESSAGE = "개 일치 (";
    private static final String STATISTICS_SECOND_PRIZE_MESSAGE = "5개 일치, 보너스 볼 일치 (";
    private static final String STATISTICS_WON_MESSAGE = "원)- ";
    private static final String STATISTICS_COUNT_MESSAGE = "개";


    public static void printPurchasedLotto(PurchasedLotto purchasedLotto) {
        System.out.println(purchasedLotto.getLottos().size() + PURCHASE_COUNT_MESSAGE);
        for (Lotto lotto : purchasedLotto.getLottos()) {
            System.out.print(LEFT_BRACKET);
            String lottoNumbers = String.join(DELIMITER, lotto.getLottoNumbers().stream()
                    .map(lottoNumber -> String.valueOf(lottoNumber.getNumber()))
                    .collect(Collectors.toList()));
            System.out.print(lottoNumbers);
            System.out.println(RIGHT_BRACKET);
        }
        System.out.print(System.lineSeparator());
    }

    public static void printFinalStatistic(PrizeResult result) {
        System.out.println(System.lineSeparator() + WINNING_STATISTIC);
        System.out.println(OUTLINE);
        Map<WinnerPrice, Integer> prizeResult = result.getPrizeResult();
        for (WinnerPrice winnerPrice : result.validWinnerPrices()) {
            printEachStatistic(winnerPrice, prizeResult);
        }
    }

    private static void printEachStatistic(WinnerPrice winnerPrice, Map<WinnerPrice, Integer> prizeResult) {
        if (winnerPrice == WinnerPrice.SECOND) {
            System.out.print(STATISTICS_SECOND_PRIZE_MESSAGE
                    + winnerPrice.getPrize() + STATISTICS_WON_MESSAGE
                    + prizeResult.get(winnerPrice) + STATISTICS_COUNT_MESSAGE
                    + System.lineSeparator());
            return;
        }
        System.out.print(winnerPrice.getMatched() + STATISTICS_PRIZE_MESSAGE
                + winnerPrice.getPrize() + STATISTICS_WON_MESSAGE
                + prizeResult.get(winnerPrice) + STATISTICS_COUNT_MESSAGE
                + System.lineSeparator());
    }

    /**
     * 총 수익률은 0.35입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)
     */
}
