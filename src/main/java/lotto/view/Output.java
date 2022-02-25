package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Rank;

public class Output {
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String REQUEST_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";
    private static final String LOTTO_COUNT = "%d개를 구매했습니다.\n";
    private static final String LOTTO_DELIMITER = ", ";
    private static final String LOTTO_FORMAT = "[%s]\n";
    private static final String REQUEST_WIN_NUMBER = "\n지난 주 당첨 번호를 입력해 주세요.";
    private static final String REQUEST_BONUS_BALL = "보너스 볼을 입력해 주세요.";
    private static final String STATISTICS_TITLE = "\n당첨 통계\n";
    private static final String TITLE_DIVIDING_LINE = "-";
    private static final int LINE_COUNT = 9;
    private static final String RESULT_FORMAT = "%d개 일치%s(%d원) - %d개\n";
    private static final String BONUS_FORMAT = ", 보너스 볼 일치";
    private static final String PROFIT_FORMAT = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)";
    private static final String PROFIT = "이익";
    private static final String LOSS = "손해";

    public static void printErrorMessage(String errorMessage) {
        System.out.println(ERROR_PREFIX + errorMessage);
    }

    public static void printRequestPurchaseAmount() {
        System.out.println(REQUEST_PURCHASE_AMOUNT);
    }

    public static void printLottoCount(int lottoCount) {
        System.out.printf(LOTTO_COUNT, lottoCount);
    }

    public static void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            String numbers = String.join(LOTTO_DELIMITER, lotto.getLottoNumbers());
            System.out.printf(LOTTO_FORMAT,numbers);
        }
    }

    public static void requestWinNumber() {
        System.out.println(REQUEST_WIN_NUMBER);
    }

    public static void requestBonusBall() {
        System.out.println(REQUEST_BONUS_BALL);
    }

    public static void printStatisticsTitle() {
        System.out.println(STATISTICS_TITLE + TITLE_DIVIDING_LINE.repeat(LINE_COUNT));
    }

    public static void printLottoResult(LottoResult lottoResult) {
        for (Rank rank : Rank.getSortedRanks()) {
            printResult(lottoResult.getLottoResult().get(rank), rank);
        }
    }

    public static void printProfitRate(final double profitRate) {
        double profitRateOfResultFormat = Math.floor(profitRate * 100) / 100.0;

        String profitFlag = PROFIT;
        if (profitRateOfResultFormat <= 1) {
            profitFlag = LOSS;
        }
        System.out.printf(PROFIT_FORMAT, profitRateOfResultFormat, profitFlag);
    }

    private static void printResult(int lottoCount, Rank rank) {
        String bonusText = " ";
        if (rank.isBonus()) {
            bonusText = BONUS_FORMAT;
        }
        System.out.printf(RESULT_FORMAT, rank.getCount(), bonusText, rank.getMoney(), lottoCount);
    }
}
