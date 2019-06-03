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
    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    public static void main(String[] args) {
        assembler();

        generateLotteries();
        generateRank();
    }

    private static void generateLotteries() {
        money = inputView.generateInvalidUserPrice();
        long manualCount = inputView.generateInvalidManualCount(money);
        generateManualLotto(manualCount);
        long autoCount = money.calculateAutoCount(manualCount);
        outputView.outputUserBuyLottoCount(manualCount, autoCount);
        lotteries.addNewLotteries(autoCount);
    }

    private static void generateRank() {
        outputView.outputAutoLotteries();
        inputView.generateInvalidWinLotto();
        winner = inputView.generateInvalidWinBonus();
        RankResult rankResult = new RankResult(lotteries, winner, money);
        outputView.outputLotteriesResult(rankResult.getRankResult());
        outputView.outputLotteriesRate(rankResult.getRate());
    }

    private static void assembler() {
        Lotto lottoType = new Lotto();
        lottoType.setCustomLotto(new DefaultCustomLotto());
        lottoType.setCreateLotto(new DefaultCreateLotto());
        lotteries = new Lotteries(lottoType);
        winner = new Winner();
        winner.setCustomLotto(new DefaultCustomLotto());

        inputView.setLotteries(lotteries);
        inputView.setWinner(winner);
        outputView.setLotteries(lotteries);
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
