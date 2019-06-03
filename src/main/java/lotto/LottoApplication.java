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
    private static Winner winner;
    private static Money money;

    public static void main(String[] args) {
        assembler();

        generateLotteries();
        generateRank();
    }

    private static void assembler() {
        Lotto lottoType = new Lotto();
        lottoType.setCustomLotto(new DefaultCustomLotto());
        lottoType.setCreateLotto(new DefaultCreateLotto());
        lotteries = new Lotteries(lottoType);
        winner = new Winner();
        winner.setCustomLotto(new DefaultCustomLotto());
    }

    private static void generateLotteries() {
        money = InputView.generateInvalidUserPrice();
        long manualCount = InputView.generateInvalidManualCount(money);
        generateManualLotto(manualCount);
        long autoCount = money.calculateAutoCount(manualCount);
        OutputView.outputUserBuyLottoCount(manualCount, autoCount);
        lotteries.addNewLotteries(autoCount);
    }

    private static void generateRank() {
        OutputView.outputAutoLotteries(lotteries);
        winner = InputView.generateInvalidWinLotto(winner);
        winner = InputView.generateInvalidWinBonus(winner);
        RankResult rankResult = new RankResult(lotteries, winner, money);
        OutputView.outputLotteriesResult(rankResult.getRankResult());
        OutputView.outputLotteriesRate(rankResult.getRate());
    }

    private static void generateManualLotto(long manualCount) {
        if (manualCount != 0) {
            generateInvalidManualLotto(manualCount);
        }
    }

    private static void generateInvalidManualLotto(long manualCount) {
        OutputView.titleInputAutoLotto();
        for (int i = 0; i < manualCount; i++) {
            lotteries = InputView.generateInvalidLotto(lotteries);
        }
    }
}
