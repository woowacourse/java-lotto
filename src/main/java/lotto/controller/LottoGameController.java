package lotto.controller;

import lotto.domain.*;
import lotto.utils.AutoLottoGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGameController {

    private final LottoGame lottoGame = new LottoGame();

    public void run() {
        buyAutoLotto();
        Lotto lotto = InputView.askLastWinningLotto();
        WinningLotto lastWinningLotto = createLastWinningLotto(lotto);
        showLottoGameResult(lastWinningLotto);
    }

    private void buyAutoLotto() {
        Money money = InputView.askMoney();
        lottoGame.buyLottos(money, new AutoLottoGenerator());
        OutputView.printEachLotto(lottoGame.myLottos());
    }

    private WinningLotto createLastWinningLotto(Lotto lotto) {
        LottoNumber bonusNumber = createBonusNumber();
        try {
            return new WinningLotto(lotto, bonusNumber);
        } catch (Exception e) {
            OutputView.printError(e);
            return createLastWinningLotto(lotto);
        }
    }

    private LottoNumber createBonusNumber() {
        try {
            return InputView.askBonusNumber();
        } catch (Exception e) {
            OutputView.printError(e);
            return createBonusNumber();
        }
    }

    private void showLottoGameResult(WinningLotto lastWinningLotto) {
        LottoGameResult lottoGameResult = lottoGame.calculateLottoGameResult(lastWinningLotto);
        OutputView.printLottoGameResult(lottoGameResult);
    }
}
