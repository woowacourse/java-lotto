package lotto.view;

import lotto.domain.Lotteries;
import lotto.domain.Lotto;

/**
 * @author heebg
 * @version 1.0 2019-05-29
 */
public class OutputView {
    private static final String OUTPUT_BUY_LOTTO_COUNT = "수동으로 %d장, 자동으로 %d장을 구매하셨습니다.\n";
    public static final String TITLE_INPUT_AUTO_LOTTO = "수동으로 구매할 번호를 입력해 주세요.";

    public static void outputUserBuyLottoCount(long manualCount, long autoCount) {
        System.out.printf(OUTPUT_BUY_LOTTO_COUNT, manualCount, autoCount);
    }

    public static void titleInputAutoLotto() {
        System.out.println(TITLE_INPUT_AUTO_LOTTO);
    }

    public static void outputAutoLotteries(Lotteries autoLotteries) {
        for (Lotto lotto : autoLotteries) {
            System.out.println(lotto.toStringWithFormat("[","]",","));
        }
    }
}
