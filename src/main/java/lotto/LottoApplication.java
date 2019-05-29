package lotto;

import lotto.view.InputView;
import lotto.view.OutputView;

/**
 * @author heebg
 * @version 1.0 2019-05-29
 */
public class LottoApplication {
    public static void main(String[] args) {
        long userPrice = InputView.generateInvalidUserPrice();
        long manualCount = InputView.generateInvalidManualCount(userPrice);
        OutputView.outputUserBuyLottoCount(manualCount, calculateBuyAutoLottoCount(userPrice, manualCount));
    }

    private static long calculateBuyAutoLottoCount(long userPrice, long manualCount) {
        return (userPrice - manualCount*1000)/1000;
    }
}
