package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoGameController {

    private LottoGame lottoGame = new LottoGame();
    private LottoGameResult lottoGameResult = new LottoGameResult();
    private Lottos manualLottos;
    private Lottos autoLottos;

    public void run() {
        LottoAmount lottoAmount = makeLottoAmount();

        buyAndPrintLottos(lottoAmount.toManualAmountNumber(), lottoAmount.toAutoAmountNumber());

        WinningLotto lastWinningLotto = askWinningLotto();

        lottoGameResult
                = lottoGame.compareWithWinningLotto(manualLottos, autoLottos, lastWinningLotto);

        OutputView.printLottoGameResult(lottoGameResult);
    }

    private LottoAmount makeLottoAmount() {
        Money money = InputView.askMoney();

        return InputView.askLottoAmount(money);
    }

    private void buyAndPrintLottos(int manualAmount, int autoAmount) {
        manualLottos = InputView.askManualLottoNumbers(manualAmount);
        autoLottos = lottoGame.buyAutoLottos(autoAmount);

        OutputView.printTotalNumberOfLotto(manualLottos, autoLottos);
        OutputView.printEachLotto(manualLottos, autoLottos);
    }

    private WinningLotto askWinningLotto() {
        Lotto winningLotto = askWinningLottoNumbers();
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

    private Lotto askWinningLottoNumbers() {
        try {
            List<LottoNumber> winningNumbers = InputView.askLastWinningLottoNumber();
            return new Lotto(winningNumbers);
        } catch (Exception e) {
            OutputView.printError(e.getMessage());
            return askWinningLottoNumbers();
        }
    }
}
