package view;

import domain.Lotto;
import domain.PrizeResult;
import domain.PurchasedLotto;
import domain.Prize;

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
    private static final String EARNING_RATE_START_MESSAGE = "총 수익률은 ";
    private static final String EARNING_RATE_END_MESSAGE = "입니다.";


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
        Map<Prize, Integer> prizeResult = result.getPrizeResult();
        for (Prize winnerPrice : result.validWinnerPrices()) {
            printEachStatistic(winnerPrice, prizeResult);
        }
    }

    private static void printEachStatistic(Prize winnerPrize, Map<Prize, Integer> prizeResult) {
        if (winnerPrize == Prize.SECOND) {
            System.out.print(STATISTICS_SECOND_PRIZE_MESSAGE + winnerPrize.getPrize() + STATISTICS_WON_MESSAGE
                    + prizeResult.get(winnerPrize) + STATISTICS_COUNT_MESSAGE + System.lineSeparator());
            return;
        }
        System.out.print(winnerPrize.getMatched() + STATISTICS_PRIZE_MESSAGE + winnerPrize.getPrize() + STATISTICS_WON_MESSAGE
                + prizeResult.get(winnerPrize) + STATISTICS_COUNT_MESSAGE + System.lineSeparator());
    }

    public static void printEarningRate(float earningRate) {
        System.out.println(EARNING_RATE_START_MESSAGE + earningRate + EARNING_RATE_END_MESSAGE);
    }

}
