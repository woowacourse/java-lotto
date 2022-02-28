package controller;

import domain.Lotto;
import domain.LottoGame;
import domain.WinningLotto;
import view.InputView;
import view.OutputView;

public class LottoGameController {

    public void run() {
        LottoGame lottoGame = new LottoGame(InputView.inputPrice());
        OutputView.printPurchasedLotto(lottoGame);

        Lotto lotto  = new Lotto(InputView.inputWinningLottoNumbers());
        int bonus = InputView.inputBonus();
        WinningLotto winningLotto = new WinningLotto(lotto, bonus);

        lottoGame.calculatePrizeResult(winningLotto);
        OutputView.printFinalStatistic(lottoGame.getPrizeResult());
        OutputView.printEarningRate(lottoGame.calculateEarningRate());
    }

}
