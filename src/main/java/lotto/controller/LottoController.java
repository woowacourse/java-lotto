package lotto.controller;

import lotto.model.lotto.LottoGame;
import lotto.model.lotto.LottoResponse;
import lotto.model.lotto.LottoStorage;
import lotto.model.result.Rank;
import lotto.model.result.WinningResult;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void playGame() {
        LottoGame lottoGame = new LottoGame();
        LottoStorage lottoStorage = makeLottos(lottoGame);
        sendMakeLottoResult(lottoStorage.getLottoStorage());
        receiveWinningNumbers(lottoGame);
        receiveBonusBall(lottoGame);

        WinningResult winningResult = lottoGame.calcLottoWithWinningNumber();
        sendWinningResult(winningResult);
        sendRateOfReturn(winningResult, lottoGame);
    }

    private LottoStorage makeLottos(LottoGame lottoGame) {
        try {
            return lottoGame.makeLottos(inputView.inputMoney());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return makeLottos(lottoGame);
        }
    }

    private void sendMakeLottoResult(List<LottoResponse> lottoStorage) {
        outputView.printLottosSize(lottoStorage.size());
        lottoStorage.forEach(lottoResponse -> outputView.printLottos(lottoResponse.getNumbers()));
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

    private void sendWinningResult(WinningResult winningResult) {
        outputView.printResultMessage();
        for (Rank rank : winningResult.getWinningCount().keySet()) {
            outputView.printWinningResult(rank.getMatchNumber(), rank.getValue(),
                    winningResult.getWinningCount().get(rank), Rank.BONUS.getValue());
        }
    }

    private void sendRateOfReturn(WinningResult winningResult, LottoGame lottoGame) {
        double rateOfReturn = lottoGame.sendRateOfReturn(winningResult);
        outputView.printRateOfReturn(rateOfReturn);
    }
}
