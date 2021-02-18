package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoGameController {

    private final LottoGame lottoGame = new LottoGame();

    public void run() {
        Lottos lottos = buyAutoLotto();

        Lotto winningLotto = askWinningLottoNumbers();
        LottoNumber bonusNumber = InputView.askBonusNumber();

        WinningLotto lastWinningLotto = makeWinningLotto(winningLotto, bonusNumber);

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
            return makeWinningLotto(winningLotto, InputView.askBonusNumber());
        }
    }

    private Lotto askWinningLottoNumbers() {
        try {
            List<LottoNumber> winningNumbers = InputView.askLastWinningLottoNumber();
            return new Lotto(winningNumbers);
        } catch (Exception e) {
            OutputView.printError(e.getMessage());
            return askWinningLottoNumbers();
        }
    }

    private void showGameResult(Lottos lottos, WinningLotto lastWinningLotto) {
        LottoGameResult lottoGameResult = lottoGame.compareWithWinningLotto(lottos, lastWinningLotto);
        OutputView.printLottoGameResult(lottoGameResult);
    }
}
