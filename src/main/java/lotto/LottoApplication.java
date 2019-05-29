package lotto;

import lotto.domain.Lotteries;
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
        long autoCount = calculateAutoLottoCount(userPrice, manualCount);
        OutputView.outputUserBuyLottoCount(manualCount, autoCount);
        OutputView.titleInputAutoLotto();
        Lotteries lotteries = generateLottos(manualCount);
    }

    private static long calculateAutoLottoCount(long userPrice, long manualCount) {
        return (userPrice - manualCount * 1000) / 1000;
    }

    private static Lotteries generateLottos(long manualCount) {
        Lotteries lotteries = new Lotteries();
        for (int i = 0; i < manualCount; i++) {
            lotteries.add(InputView.generateInvalidLotto());
        }
        return lotteries;
    }
}
