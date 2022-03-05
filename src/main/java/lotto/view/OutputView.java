package lotto.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lotto.domain.Lotto;
import lotto.domain.Rank;

public class OutputView {
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String PAYMENT_REQUEST_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String LOTTO_BY_HAND_COUNT_REQUEST_MESSAGE = "\n수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String LOTTO_NUMBERS_BY_HAND_REQUEST_MESSAGE = "\n수동으로 구매할 번호를 입력해 주세요.";
    private static final String LOTTO_COUNT_MESSAGE = "\n수동으로 %d장, 자동으로 %d개를 구매했습니다.\n";
    private static final String LOTTO_DELIMITER = ", ";
    private static final String LOTTO_FORMAT = "[%s]\n";
    private static final String WINNING_NUMBER_REQUEST_MESSAGE = "\n지난 주 당첨 번호를 입력해 주세요.";
    private static final String BONUS_BALL_REQUEST_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String STATISTICS_TITLE = "\n당첨 통계\n";
    private static final String TITLE_DIVIDING_LINE = "-";
    private static final int LINE_COUNT = 9;
    private static final String RESULT_FORMAT = "%d개 일치%s(%d원) - %d개\n";
    private static final String BONUS_FORMAT = ", 보너스 볼 일치";
    private static final String PROFIT_FORMAT = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)";
    private static final String PROFIT_MESSAGE = "이익";
    private static final String LOSS_MESSAGE = "손해";

    public static void printErrorMessage(String errorMessage) {
        System.out.println(ERROR_PREFIX + errorMessage);
    }

    public static void printPaymentRequest() {
        System.out.println(PAYMENT_REQUEST_MESSAGE);
    }

    public static void printManualLottoCountRequest() {
        System.out.println(LOTTO_BY_HAND_COUNT_REQUEST_MESSAGE);
    }

    public static void printManualLottoNumbersRequest() {
        System.out.println(LOTTO_NUMBERS_BY_HAND_REQUEST_MESSAGE);
    }

    public static void printLottos(int manualCount, List<Lotto> lottoList) {
        printLottoCount(manualCount, lottoList.size());
        for (Lotto lotto : lottoList) {
            String numbers = lotto.getLottoNumbers().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(LOTTO_DELIMITER));
            System.out.printf(LOTTO_FORMAT, numbers);
        }
    }

    public static void printLottoCount(int handCount, int autoCount) {
        System.out.printf(LOTTO_COUNT_MESSAGE, handCount, autoCount);
    }

    public static void printWinningNumberRequest() {
        System.out.println(WINNING_NUMBER_REQUEST_MESSAGE);
    }

    public static void printBonusBallRequest() {
        System.out.println(BONUS_BALL_REQUEST_MESSAGE);
    }

    public static void printStatisticsTitle() {
        System.out.println(STATISTICS_TITLE + TITLE_DIVIDING_LINE.repeat(LINE_COUNT));
    }

    public static void printLottosResult(Map<Rank, Integer> lottoResult) {
        for (Rank rank : Rank.getSortedRanks()) {
            printLottoResult(lottoResult.get(rank), rank);
        }
    }

    private static void printLottoResult(int lottoCount, Rank rank) {
        String bonusText = " ";
        if (rank == Rank.SECOND) {
            bonusText = BONUS_FORMAT;
        }
        System.out.printf(RESULT_FORMAT, rank.getCount(), bonusText, rank.getPrizeMoney().getMoney(), lottoCount);
    }

    public static void printProfitRate(final double profitRate) {
        double roundDownProfitRate = Math.floor(profitRate * 100) / 100.0;

        if (roundDownProfitRate <= 1) {
            System.out.printf(PROFIT_FORMAT, roundDownProfitRate, LOSS_MESSAGE);
            return;
        }
        System.out.printf(PROFIT_FORMAT, roundDownProfitRate, PROFIT_MESSAGE);
    }
}
