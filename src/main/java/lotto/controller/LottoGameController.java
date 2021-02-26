package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGameController {

    public void run() {
        LottoAmount lottoAmount = makeLottoAmount();

        LottoGame lottoGame = buyTwoTypeLottos(lottoAmount);
        printEachLottos(lottoGame);

        WinningLotto lastWinningLotto = askWinningLotto();
        LottoGameResult lottoGameResult = lottoGame.draw(lastWinningLotto);

        OutputView.printLottoGameResult(lottoGameResult);
    }

    private LottoAmount makeLottoAmount() {
        Money money = InputView.askMoney();

        return InputView.askLottoAmount(money);
    }

    private LottoGame buyTwoTypeLottos(LottoAmount lottoAmount) {
        LottoGame lottoGame = new LottoGame();

        Lottos manualLottos = InputView.askManualLottoNumbers(lottoAmount.toManualAmountNumber());
        lottoGame.buyManualLottos(manualLottos);
        lottoGame.buyAutoLottos(lottoAmount.toAutoAmountNumber());

        return lottoGame;
    }

    private void printEachLottos(LottoGame lottoGame) {
        OutputView.printTotalNumberOfLotto(lottoGame.toManualLottos(), lottoGame.toAutoLottos());
        OutputView.printEachLotto(lottoGame.toManualLottos(), lottoGame.toAutoLottos());
    }

    private WinningLotto askWinningLotto() {
        Lotto winningLotto = InputView.askLastWinningLottoNumber();
        LottoNumber bonusNumber = InputView.askBonusNumber();

        return makeWinningLotto(winningLotto, bonusNumber);
    }

    private WinningLotto makeWinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
        try {
            return new WinningLotto(winningLotto, bonusNumber);
        } catch (Exception e) {
            OutputView.printError(e.getMessage());
            return makeWinningLotto(winningLotto, InputView.askBonusNumber());
        }
    }
}
