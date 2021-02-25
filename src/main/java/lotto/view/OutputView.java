package lotto.view;

import java.text.DecimalFormat;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Rank;

public class OutputView {

    private static final String INPUT_MONEY_MESSAGE = "구입 금액을 입력해 주세요.";
    private static final String LOTTO_COUNT_MESSAGE = "개를 구매했습니다.";
    private static final String LAST_WEEK_LOTTO_MESSAGE = "\n지난 주 당첨 번호를 입력해 주세요.";
    private static final String BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String RESULT_TITLE_MESSAGE = "\n당첨 통계\n----------";
    private static final String AMOUNT = "개";
    private static final String EARNING_RATE_MESSAGE = "총 수익률은 %s입니다.";
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

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

    public static void displayResult(Rank rank, int count) {
        System.out.println(rank + Integer.toString(count) + AMOUNT);
    }

    public static void displayEarningRate(double earningRate) {
        System.out.printf(EARNING_RATE_MESSAGE, DECIMAL_FORMAT.format(earningRate));
    }
}
