package lotto;

import lotto.domain.*;
import lotto.domain.customlotto.DefaultCustomLotto;
import lotto.domain.autocreatelotto.DefaultAutoCreateLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

/**
 * @author heebg
 * @version 1.0 2019-05-29
 */
public class LottoApplication {
    private static Lotteries lotteries;
    private static Winner winner;
    private static Money money;

    public static void main(String[] args) {
        assembler();

        generateLotteries();
        generateRank();
    }

    private static void assembler() {
        lotteries = new Lotteries();
        winner = new Winner();
        winner.setCustomLotto(new DefaultCustomLotto());
    }

    private static void generateLotteries() {
        money = InputView.generateInvalidUserPrice();
        long manualCount = InputView.generateInvalidManualCount(money);
        generateManualLotto(manualCount, new DefaultCustomLotto());
        long autoCount = money.calculateAutoCount(manualCount);
        OutputView.outputUserBuyLottoCount(manualCount, autoCount);
        lotteries.addAutoLotteries(autoCount, new DefaultAutoCreateLotto());
    }

    private static void generateRank() {
        OutputView.outputAutoLotteries(lotteries);
        winner = InputView.generateInvalidWinLotto(winner);
        winner = InputView.generateInvalidWinBonus(winner);
        RankResult rankResult = new RankResult(lotteries, winner, money);
        OutputView.outputLotteriesResult(rankResult.getRankResult());
        OutputView.outputLotteriesRate(rankResult.getRate());
    }

    private static void generateManualLotto(long manualCount, CustomLotto customLotto) {
        if (manualCount != 0) {
            generateInvalidManualLotto(manualCount, customLotto);
        }
    }

    private static void generateInvalidManualLotto(long manualCount, CustomLotto customLotto) {
        OutputView.titleInputAutoLotto();
        for (int i = 0; i < manualCount; i++) {
            lotteries = InputView.generateInvalidLotto(lotteries, customLotto);
        }
    }
}
