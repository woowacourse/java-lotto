package lotto.view;

import java.text.DecimalFormat;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class OutputView {

    private static final String INPUT_MONEY_MESSAGE = "구입 금액을 입력해 주세요.";
    private static final String LOTTO_COUNT_MESSAGE = "개를 구매했습니다.";
    private static final String LAST_WEEK_LOTTO_MESSAGE = "\n지난 주 당첨 번호를 입력해 주세요.";
    private static final String BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String RESULT_TITLE_MESSAGE = "\n당첨 통계\n----------";
    private static final String AMOUNT = "개";
    private static final String EARNING_RATE_MESSAGE = "총 수익률은 %s입니다.";
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");
    private static final String LOTTO_RESULT_MESSAGE = "%d개 일치, (%d)원 - %d개\n";
    private static final String LOTTO_RESULT_SECOND_MESSAGE = "%d개 일치, 보너스 볼 일치 (%d)원 - %d개\n";

    public static void requestMoneyMessage() {
        System.out.println(INPUT_MONEY_MESSAGE);
    }

    public static void buyLottoMessage(int count) {
        System.out.println(count + LOTTO_COUNT_MESSAGE);
    }

    public static void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getLottoNumbers());
        }
    }

    public static void requestWinningNumberMessage() {
        System.out.println(LAST_WEEK_LOTTO_MESSAGE);
    }

    public static void requestBonusBallNumberMessage() {
        System.out.println(BONUS_BALL_MESSAGE);
    }

    public static void displayResultMessage() {
        System.out.println(RESULT_TITLE_MESSAGE);
    }

    public static void displayResult(int matchCount, int prize, int count) {
        if (prize == 30_000_000) {
            System.out.printf(LOTTO_RESULT_SECOND_MESSAGE, matchCount, prize, count);
        }
        System.out.printf(LOTTO_RESULT_MESSAGE, matchCount, prize, count);
    }

    public static void displayEarningRate(double earningRate) {
        System.out.printf(EARNING_RATE_MESSAGE, DECIMAL_FORMAT.format(earningRate));
    }
}
