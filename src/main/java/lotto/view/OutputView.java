package lotto.view;

import java.text.DecimalFormat;
import java.util.List;
import lotto.domain.Lotto;

public class OutputView {

    private static final String LOTTO_COUNT_MESSAGE = "개를 구매했습니다.";
    private static final String RESULT_TITLE_MESSAGE = "\n당첨 통계\n----------";
    private static final String EARNING_RATE_MESSAGE = "총 수익률은 %s입니다.";
    private static final String LOTTO_RESULT_MESSAGE = "%d개 일치, (%d)원 - %d개\n";
    private static final String LOTTO_RESULT_SECOND_MESSAGE = "%d개 일치, 보너스 볼 일치 (%d)원 - %d개\n";
    public static final int SECOND_PRIZE = 30_000_000;
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

    public static void buyLottoMessage(int count) {
        System.out.println(count + LOTTO_COUNT_MESSAGE);
    }

    public static void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getLottoNumbers());
        }
    }

    public static void displayResultMessage() {
        System.out.println(RESULT_TITLE_MESSAGE);
    }

    public static void displayResult(int matchCount, int prize, int count) {
        if (prize == SECOND_PRIZE) {
            System.out.printf(LOTTO_RESULT_SECOND_MESSAGE, matchCount, prize, count);
        }
        System.out.printf(LOTTO_RESULT_MESSAGE, matchCount, prize, count);
    }

    public static void displayEarningRate(double earningRate) {
        System.out.printf(EARNING_RATE_MESSAGE, DECIMAL_FORMAT.format(earningRate));
    }
}
