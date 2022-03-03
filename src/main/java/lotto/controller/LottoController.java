package lotto.controller;

import java.util.ArrayList;
import java.util.List;

import lotto.model.lotto.*;
import lotto.model.result.Money;
import lotto.model.result.RateOfReturn;
import lotto.model.result.WinningResult;
import lotto.model.winningnumber.WinningLotto;
import lotto.model.winningnumber.WinningLottoResponse;
import lotto.utils.ConverterUtils;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void playGame() {
        LottoGame lottoGame = new LottoGame();
        Money money = receiveMoney();
        ManualCount manual = receiveManualCount();
        final int manualCount = manual.getNumber();
        List<Lotto> manualLottos = receiveManualLottos(lottoGame, manual);

        LottoStorage lottos = lottoGame.makeLottos(new LottoCount(money.getNumber(), manualCount), manualLottos);
        OutputView.printLottos(manualCount, lottos.getLottoStorage());
        WinningResult winnings = lottoGame.calcWinningNumber(lottos, new WinningLottoResponse(receiveWinningLotto()));
        sendResult(lottoGame, money.getNumber(), winnings);
    }

    private Money receiveMoney() {
        try {
            return new Money(ConverterUtils.convertStringToInt(InputView.inputMoney()));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return receiveMoney();
        }
    }

    private ManualCount receiveManualCount() {
        try {
            return new ManualCount(ConverterUtils.convertStringToInt(InputView.inputManualLottoCount()));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return receiveManualCount();
        }
    }

    private List<Lotto> receiveManualLottos(LottoGame lottoGame, ManualCount manualCount) {
        InputView.inputManualLottoMessage();
        List<Lotto> lottos = new ArrayList<>();
        while(!lottoGame.isPossibleMakeLottos(manualCount)) {
            lottoGame.makeManualLottos(lottos, receiveManualLotto(), manualCount);
        }
        return lottos;
    }

    private Lotto receiveManualLotto() {
        try {
            return new Lotto(InputView.inputManualLottos());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return receiveManualLotto();
        }
    }

    private WinningLotto receiveWinningLotto() {
        try {
            return new WinningLotto(InputView.inputWinningLotto(), InputView.inputBonusBall());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return receiveWinningLotto();
        }
    }

    private void sendResult(LottoGame lottoGame, long money, WinningResult winningResult) {
        OutputView.printWinningResult(winningResult);
        RateOfReturn rateOfReturn = lottoGame.storeMoneyInRateOfReturn(money);
        OutputView.printRateOfReturn(lottoGame.sendRateOfReturn(rateOfReturn, winningResult));
    }
}
