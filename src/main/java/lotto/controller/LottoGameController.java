package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGameController {

    private final LottoGame lottoGame = new LottoGame();

    public void run() {
        Lottos lottos = buyAutoLotto();

        WinningLotto lastWinningLotto =
                makeWinningLotto(lastWinningLotto(), InputView.askBonusNumber());

        showGameResult(lottos, lastWinningLotto);
    }

    private Lottos buyAutoLotto() {
        Money money = InputView.askMoney();
        Lottos lottos = lottoGame.buyLottos(money);

        OutputView.printTotalNumberOfLotto(lottos);
        OutputView.printEachLotto(lottos);

        return lottos;
    }

    private WinningLotto makeWinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
        try {
            return new WinningLotto(winningLotto, bonusNumber);
        } catch (Exception e) {
            OutputView.printError(e.getMessage());
            bonusNumber = InputView.askBonusNumber();
            return makeWinningLotto(winningLotto, bonusNumber);
        }
    }

    private Lotto lastWinningLotto() {
        try {
            return new Lotto(InputView.askLastWinningLottoNumber());
        } catch (Exception e) {
            OutputView.printError(e.getMessage());
            return lastWinningLotto();
        }
    }

    private void showGameResult(Lottos lottos, WinningLotto lastWinningLotto) {
        LottoGameResult lottoGameResult = lottoGame.compareWithWinningLotto(lottos, lastWinningLotto);
        OutputView.printLottoGameResult(lottoGameResult);
    }
}
