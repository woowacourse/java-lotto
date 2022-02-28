package lotto.controller;

import java.util.List;

import lotto.model.bonusball.BonusBall;
import lotto.model.bonusball.BonusBallResponse;
import lotto.model.lotto.LottoCount;
import lotto.model.lotto.LottoGame;
import lotto.model.lotto.LottoResponse;
import lotto.model.lotto.LottoStorage;
import lotto.model.result.Money;
import lotto.model.result.Rank;
import lotto.model.result.RateOfReturn;
import lotto.model.result.WinningResult;
import lotto.model.winningnumber.LottoWinningNumber;
import lotto.model.winningnumber.LottoWinningNumberResponse;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void playGame() {
        Money money = receiveMoney();
        LottoGame lottoGame = new LottoGame();
        LottoStorage lottoStorage = lottoGame.makeLottos(new LottoCount(money.getNumber()));
        sendMakeLottoResult(lottoStorage.getLottoStorage());

        LottoWinningNumber lottoWinningNumber = receiveWinningNumbers();
        BonusBall bonusBall = receiveBonusBall(lottoGame, lottoWinningNumber);

        WinningResult winningResult = receiveWinningResult(lottoGame, lottoStorage, bonusBall, lottoWinningNumber);
        sendResult(lottoGame, money.getNumber(), winningResult);
    }

    private Money receiveMoney() {
        try {
            return new Money(inputView.inputMoney());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return receiveMoney();
        }
    }

    private void sendMakeLottoResult(List<LottoResponse> lottoStorage) {
        outputView.printLottosSize(lottoStorage.size());
        lottoStorage.forEach(lottoResponse -> outputView.printLottos(lottoResponse.getNumbers()));
    }

    private LottoWinningNumber receiveWinningNumbers() {
        try {
            return new LottoWinningNumber(inputView.inputWinningNumbers());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return receiveWinningNumbers();
        }
    }

    private BonusBall receiveBonusBall(LottoGame lottoGame, LottoWinningNumber lottoWinningNumber) {
        try {
            return lottoGame.storeBonusBall(lottoWinningNumber, inputView.inputBonusBall());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return receiveBonusBall(lottoGame, lottoWinningNumber);
        }
    }

    private WinningResult receiveWinningResult(LottoGame lottoGame, LottoStorage lottoStorage, BonusBall bonusBall,
                                               LottoWinningNumber lottoWinningNumber) {
        return lottoGame.calcLottoWithWinningNumber(lottoStorage,
                new BonusBallResponse(bonusBall.getNumber()),
                new LottoWinningNumberResponse(lottoWinningNumber.getWinningNumbers()));
    }

    private void sendResult(LottoGame lottoGame, String money, WinningResult winningResult) {
        sendWinningResult(winningResult);
        RateOfReturn rateOfReturn = receiveRateOfReturn(lottoGame, money);
        sendRateOfReturn(lottoGame, rateOfReturn, winningResult);
    }

    private void sendWinningResult(WinningResult winningResult) {
        outputView.printResultMessage();
        for (Rank rank : winningResult.getWinningCount().keySet()) {
            outputView.printWinningResult(rank.getMatchNumber(), rank.getValue(),
                    winningResult.getWinningCount().get(rank), Rank.BONUS.getValue());
        }
    }

    private void sendRateOfReturn(LottoGame lottoGame, RateOfReturn rateOfReturn, WinningResult winningResult) {
        double result = lottoGame.sendRateOfReturn(rateOfReturn, winningResult);
        outputView.printRateOfReturn(result);
    }

    private RateOfReturn receiveRateOfReturn(LottoGame lottoGame, String money) {
        return lottoGame.storeMoneyInRateOfReturn(money);
    }
}
