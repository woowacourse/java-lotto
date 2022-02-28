package lotto.controller;

import lotto.model.bonusball.BonusBall;
import lotto.model.bonusball.BonusBallResponse;
import lotto.model.lotto.LottoGame;
import lotto.model.lotto.LottoResponse;
import lotto.model.lotto.LottoStorage;
import lotto.model.result.Rank;
import lotto.model.result.RateOfReturn;
import lotto.model.result.WinningResult;
import lotto.model.winningnumber.LottoWinningNumber;
import lotto.model.winningnumber.LottoWinningNumberResponse;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    private String money;

    public void playGame() {
        LottoGame lottoGame = new LottoGame();
        LottoStorage lottoStorage = makeLottos(lottoGame);
        sendMakeLottoResult(lottoStorage.getLottoStorage());
        LottoWinningNumber lottoWinningNumber = receiveWinningNumbers(lottoGame);
        BonusBall bonusBall = receiveBonusBall(lottoWinningNumber, lottoGame);

        WinningResult winningResult = receiveWinningResult(lottoGame, lottoStorage, bonusBall, lottoWinningNumber);
        sendResult(winningResult, lottoGame);
    }

    private LottoStorage makeLottos(LottoGame lottoGame) {
        try {
            money = inputView.inputMoney();
            return lottoGame.makeLottos(money);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return makeLottos(lottoGame);
        }
    }

    private void sendMakeLottoResult(List<LottoResponse> lottoStorage) {
        outputView.printLottosSize(lottoStorage.size());
        lottoStorage.forEach(lottoResponse -> outputView.printLottos(lottoResponse.getNumbers()));
    }

    private LottoWinningNumber receiveWinningNumbers(LottoGame lottoGame) {
        try {
            return lottoGame.storeWinningNumber(inputView.inputWinningNumbers());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return receiveWinningNumbers(lottoGame);
        }
    }

    private BonusBall receiveBonusBall(LottoWinningNumber lottoWinningNumber, LottoGame lottoGame) {
        try {
            return lottoGame.storeBonusBall(lottoWinningNumber, inputView.inputBonusBall());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return receiveBonusBall(lottoWinningNumber, lottoGame);
        }
    }

    private WinningResult receiveWinningResult(LottoGame lottoGame, LottoStorage lottoStorage,
                                               BonusBall bonusBall, LottoWinningNumber lottoWinningNumber) {
        return lottoGame.calcLottoWithWinningNumber(lottoStorage,
                new BonusBallResponse(bonusBall.getNumber()),
                new LottoWinningNumberResponse(lottoWinningNumber.getWinningNumbers()));
    }

    private void sendResult(WinningResult winningResult, LottoGame lottoGame) {
        sendWinningResult(winningResult);
        RateOfReturn rateOfReturn = receiveRateOfReturn(money, lottoGame);
        sendRateOfReturn(rateOfReturn, winningResult, lottoGame);
    }

    private void sendWinningResult(WinningResult winningResult) {
        outputView.printResultMessage();
        for (Rank rank : winningResult.getWinningCount().keySet()) {
            outputView.printWinningResult(rank.getMatchNumber(), rank.getValue(),
                    winningResult.getWinningCount().get(rank), Rank.BONUS.getValue());
        }
    }

    private void sendRateOfReturn(RateOfReturn rateOfReturn, WinningResult winningResult, LottoGame lottoGame) {
        double result = lottoGame.sendRateOfReturn(rateOfReturn, winningResult);
        outputView.printRateOfReturn(result);
    }

    private RateOfReturn receiveRateOfReturn(String money, LottoGame lottoGame) {
        return lottoGame.storeMoneyInRateOfReturn(money);
    }
}
