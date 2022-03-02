package lotto.controller;

import lotto.model.lotto.LottoCount;
import lotto.model.lotto.LottoGame;
import lotto.model.lotto.LottoStorage;
import lotto.model.result.Money;
import lotto.model.result.Rank;
import lotto.model.result.RateOfReturn;
import lotto.model.result.WinningResult;
import lotto.model.winningnumber.WinningLotto;
import lotto.model.winningnumber.WinningLottoResponse;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void playGame() {
        Money money = receiveMoney();
        LottoGame lottoGame = new LottoGame();
        LottoStorage lottoStorage = lottoGame.makeLottos(new LottoCount(money.getNumber()));
        outputView.printLottos(lottoStorage.getLottoStorage());

        WinningLotto winningNumber = receiveWinningLotto();
        WinningResult winningResult = lottoGame.calcWinningNumber(lottoStorage,
                new WinningLottoResponse(winningNumber.getWinningNumbers(), winningNumber.getBonusBall()));
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

    private WinningLotto receiveWinningLotto() {
        try {
            return new WinningLotto(inputView.inputWinningNumbers(), inputView.inputBonusBall());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return receiveWinningLotto();
        }
    }

    private void sendResult(LottoGame lottoGame, String money, WinningResult winningResult) {
        outputView.printResultMessage();
        for (Rank rank : winningResult.getWinningCount().keySet()) {
            outputView.printWinningResult(rank.getMatchNumber(), rank.getValue(),
                    winningResult.getWinningCount().get(rank), Rank.BONUS.getValue());
        }
        RateOfReturn rateOfReturn = lottoGame.storeMoneyInRateOfReturn(money);
        outputView.printRateOfReturn(lottoGame.sendRateOfReturn(rateOfReturn, winningResult));
    }
}

