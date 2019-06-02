package lotto;

import lotto.domain.*;
import lotto.domain.customlotto.DefaultCustomLotto;
import lotto.domain.makeuplotto.DefaultCreateLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

/**
 * @author heebg
 * @version 1.0 2019-05-29
 */
public class LottoApplication {
    private static Lotteries lotteries;
    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    public static void main(String[] args) {
        assembler();

        long userPrice = inputView.generateInvalidUserPrice();
        long manualCount = inputView.generateInvalidManualCount(userPrice);
        generateManualLotto(manualCount);
        long autoCount = calculateAutoLottoCount(userPrice, manualCount);
        outputView.outputUserBuyLottoCount(manualCount, autoCount);
        lotteries.addNewLotteries(autoCount);

        outputView.outputAutoLotteries();
    }

    private static void assembler() {
        Lotto lotto = new Lotto();
        lotto.setCustomLotto(new DefaultCustomLotto());
        lotto.setCreateLotto(new DefaultCreateLotto());

        lotteries = new Lotteries(lotto);

        inputView.setLotteries(lotteries);
        outputView.setLotteries(lotteries);
    }

    private static long calculateAutoLottoCount(long userPrice, long manualCount) {
        return (userPrice - manualCount * 1000) / 1000;
    }

    private static void generateManualLotto(long manualCount) {
        if (manualCount != 0) {
            generateInvalidManualLotto(manualCount);
        }
    }

    private static void generateInvalidManualLotto(long manualCount) {
        outputView.titleInputAutoLotto();
        for (int i = 0; i < manualCount; i++) {
            inputView.generateInvalidLotto();
        }
    }
}
