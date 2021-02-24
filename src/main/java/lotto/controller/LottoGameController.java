package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoGameController {

    public void run() {
        LottoAmount lottoAmount = makeLottoAmount();

        LottoGame lottoGame = buyTwoTypeLottos(lottoAmount);
        printEachLottos(lottoGame);

        WinningLotto lastWinningLotto = askWinningLotto();
        LottoGameResult lottoGameResult = getRankOfLottoGame(lottoGame, lastWinningLotto);

        OutputView.printLottoGameResult(lottoGameResult);
    }

    private LottoAmount makeLottoAmount() {
        Money money = InputView.askMoney();

        return InputView.askLottoAmount(money);
    }

    private LottoGame buyTwoTypeLottos(LottoAmount lottoAmount) {
        LottoGame lottoGame = new LottoGame();
        List<Lotto> manualLottos = InputView.askManualLottoNumbers(lottoAmount.toManualAmountNumber());
        lottoGame.buyManualLottos(manualLottos);
        lottoGame.buyAutoLottos(lottoAmount.toAutoAmountNumber());

        return lottoGame;
    }

    private void printEachLottos(LottoGame lottoGame) {
        OutputView.printTotalNumberOfLotto(lottoGame.toManualLottos(), lottoGame.toAutoLottos());
        OutputView.printEachLotto(lottoGame.toManualLottos(), lottoGame.toAutoLottos());
    }

    private WinningLotto askWinningLotto() {
        Lotto winningLotto = askWinningLottoNumbers();
        LottoNumber bonusNumber = InputView.askBonusNumber();

        return makeWinningLotto(winningLotto, bonusNumber);
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

    private WinningLotto makeWinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
        try {
            return new WinningLotto(winningLotto, bonusNumber);
        } catch (Exception e) {
            OutputView.printError(e.getMessage());
            return makeWinningLotto(winningLotto, InputView.askBonusNumber());
        }
    }

    private LottoGameResult getRankOfLottoGame(LottoGame lottoGame, WinningLotto lastWinningLotto) {
        LottoGameResult lottoGameResult = new LottoGameResult();
        Lottos manualLottos = lottoGame.toManualLottos();
        Lottos autoLottos = lottoGame.toAutoLottos();

        manualLottos.addMatchLotto(lastWinningLotto, lottoGameResult);
        autoLottos.addMatchLotto(lastWinningLotto, lottoGameResult);

        return lottoGameResult;
    }
}
