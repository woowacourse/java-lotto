package lotto.controller;

import lotto.model.lotto.LottoGame;
import lotto.model.lotto.LottoStorage;
import lotto.model.result.WinningResult;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void playGame() {
        LottoGame lottoGame = new LottoGame();
        LottoStorage lottoStorage = makeLottos(lottoGame);
        outputView.printLottos(lottoStorage.getLottoStorage());
        receiveWinningNumbers(lottoGame);
        receiveBonusBall(lottoGame);

        WinningResult winningResult = lottoGame.calcLottoWithWinningNumber();

        sendStatistics(winningResult, lottoGame);
    }

    private LottoStorage makeLottos(LottoGame lottoGame) {
        try {
            return lottoGame.makeLottos(inputView.inputMoney());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return makeLottos(lottoGame);
        }
    }

    private void receiveWinningNumbers(LottoGame lottoGame) {
        try {
            lottoGame.storeWinningNumber(inputView.inputWinningNumbers());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            receiveWinningNumbers(lottoGame);
        }
    }

    private void receiveBonusBall(LottoGame lottoGame) {
        try {
            lottoGame.storeBonusBall(inputView.inputBonusBall());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            receiveBonusBall(lottoGame);
        }
    }

    private void sendStatistics(WinningResult winningResult, LottoGame lottoGame) {
        outputView.printResultMessage();
        double rateOfReturn = lottoGame.sendResult(winningResult);
        outputView.printRateOfReturn(rateOfReturn);
    }
}
