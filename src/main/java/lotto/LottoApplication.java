package lotto;

import lotto.view.InputView;

/**
 * @author heebg
 * @version 1.0 2019-05-29
 */
public class LottoApplication {
    public static void main(String[] args) {
        double userPrice = InputView.generateInvalidUserPrice();
        double manualCount = InputView.generateInvalidManualCount(userPrice);
    }
}
