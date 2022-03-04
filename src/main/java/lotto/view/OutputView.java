package lotto.view;

import java.util.List;

import lotto.model.lotto.LottoResponse;
import lotto.model.lotto.result.Rank;
import lotto.model.lotto.result.WinningResult;

public class OutputView {
    private static final String PURCHASE_MESSAGE = "수동으로 %d장, 자동으로 %d개를 구매했습니다.";
    private static final String RATE_OF_RETURN_MESSAGE = "당첨 통계";
    private static final String LINE = "---------";
    private static final String RESULT_LOTTO_BONUS_BALL = "%d개 일치, 보너스 볼 일치(%d원) - %d개%n";
    private static final String RESULT_LOTTO_NUMBER = "%d개 일치 (%d원) - %d개%n";
    private static final String RESULT_RATE_OF_RETURN_LOSS = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
    private static final String RESULT_RATE_OF_RETURN_GAIN = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 이득라는 의미임)";
    private static final int PROFIT_STANDARD = 1;

    private OutputView() {
    }

    public static void printErrorMessage(String message) {
        printMessage(message);
    }

    public static void printLottosSize(int manualCount, int size) {
        printNewLine();
        System.out.printf(PURCHASE_MESSAGE, manualCount, size - manualCount);
        printNewLine();
    }

    public static void printLottos(int manualCount, List<LottoResponse> lottoStorage) {
        printLottosSize(manualCount, lottoStorage.size());
        lottoStorage.forEach(lottoResponse -> System.out.println(lottoResponse.getNumbers()));
    }

    public static void printResultMessage() {
        printNewLine();
        printMessage(RATE_OF_RETURN_MESSAGE);
        printMessage(LINE);
    }

    public static void printWinningResult(WinningResult winningResult) {
        printResultMessage();
        for (Rank rank : winningResult.getWinningCount().keySet()) {
            printResult(rank.getMatchNumber(), rank.getValue(), winningResult.getWinningCount().get(rank),
                    Rank.BONUS.getValue());
        }
    }

    private static void printResult(int matchNumber, long value, int count, long bonusMoney) {
        if (value == bonusMoney) {
            System.out.printf(RESULT_LOTTO_BONUS_BALL, matchNumber, value, count);
            return;
        }
        System.out.printf(RESULT_LOTTO_NUMBER, matchNumber, value, count);
    }

    public static void printRateOfReturn(double rateOfReturn) {
        if (rateOfReturn < PROFIT_STANDARD) {
            System.out.printf(RESULT_RATE_OF_RETURN_LOSS, rateOfReturn);
            return;
        }
        System.out.printf(RESULT_RATE_OF_RETURN_GAIN, rateOfReturn);
    }

    private static void printNewLine() {
        System.out.println();
    }

    private static void printMessage(String message) {
        System.out.println(message);
    }
}
