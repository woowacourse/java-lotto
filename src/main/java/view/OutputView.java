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
        System.out.println("당첨 통계");
        System.out.println("---------");
        Map<WinnerPrice, Integer> prizeResult = result.getPrizeResult();
        for (WinnerPrice winnerPrice : result.validWinnerPrices()) {
            printEachStatistic(winnerPrice, prizeResult);
        }
    }

    private static void printEachStatistic(WinnerPrice winnerPrice, Map<WinnerPrice, Integer> prizeResult) {
        if (winnerPrice == WinnerPrice.SECOND) {
            System.out.print("5개 일치, 보너스 볼 일치 ("
                    + winnerPrice.getPrize() + "원)- "
                    + prizeResult.get(winnerPrice) + "개"
                    + System.lineSeparator());
            return;
        }
        System.out.print(winnerPrice.getMatched() + "개 일치 ("
                + winnerPrice.getPrize() + "원)- "
                + prizeResult.get(winnerPrice) + "개"
                + System.lineSeparator());
    }

    /**
     * 당첨 통계
     * ---------
     * 3개 일치 (5000원)- 1개
     * 4개 일치 (50000원)- 0개
     * 5개 일치 (1500000원)- 0개
     * 5개 일치, 보너스 볼 일치(30000000원) - 0개
     * 6개 일치 (2000000000원)- 0개
     * 총 수익률은 0.35입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)
     */
}
