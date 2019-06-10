package lotto;

import lotto.domain.*;
import lotto.domain.customcreatelotto.CustomCreateLotto;
import lotto.domain.customcreatelotto.DefaultCustomCreateCreateLotto;
import lotto.domain.autocreatelotto.DefaultAutoCreateLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

/**
 * @author heebg
 * @version 1.0 2019-05-29
 */
public class LottoApplication {
    public static void main(String[] args) {
        Money money = InputView.generateInvalidUserPrice();
        long manualCount = InputView.generateInvalidManualCount(money);
        Lotteries lotteries = generateManualLotto(manualCount, new DefaultCustomCreateCreateLotto());

        lotteries.addAutoLotteries(money.calculateAutoCount(manualCount), new DefaultAutoCreateLotto());
        OutputView.outputUserBuyLottoCount(manualCount, money.calculateAutoCount(manualCount));
        OutputView.outputAutoLotteries(lotteries);

        Winner winner = InputView.generateWinner(new DefaultCustomCreateCreateLotto());
        RankResult rankResult = new RankResult(lotteries, winner, money);
        OutputView.outputLotteriesResult(rankResult);
    }

    private static Lotteries generateManualLotto(long manualCount, CustomCreateLotto customCreateLotto) {
        Lotteries lotteries = new Lotteries();
        if (manualCount != 0) {
            return generateInvalidManualLotto(lotteries, manualCount, customCreateLotto);
        }
        return lotteries;
    }

    private static Lotteries generateInvalidManualLotto(Lotteries lotteries, long manualCount, CustomCreateLotto customCreateLotto) {
        OutputView.titleInputAutoLotto();
        for (int i = 0; i < manualCount; i++) {
            lotteries = InputView.generateInvalidLotto(lotteries, customCreateLotto);
        }
        return lotteries;
    }
}
