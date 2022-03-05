package lotto.controller;

import java.util.ArrayList;
import java.util.List;

import lotto.model.lotto.*;
import lotto.model.lotto.result.Money;
import lotto.model.lotto.result.RateOfReturn;
import lotto.model.lotto.result.WinningResult;
import lotto.model.lotto.WinningLotto;
import lotto.dto.WinningLottoResponse;
import lotto.utils.ConverterUtils;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void playGame() {
        LottoGame lottoGame = new LottoGame();
        Money money = makeMoney();
        ManualCount manual = receiveManualCount();
        List<Lotto> manualLottos = receiveManualLottos(lottoGame, manual);

        LottoStorage lottos = lottoGame.makeLottos(new LottoCount(money.getNumber(), manual.getNumber()), manualLottos);
        OutputView.printLottos(manual.getNumber(), lottos.getLottoStorage());
        WinningResult winnings = lottoGame.calcWinningNumber(lottos, new WinningLottoResponse(receiveWinningLotto()));
        sendResult(lottoGame, money.getNumber(), winnings);
    }

    private Money makeMoney() {
        try {
            return new Money(ConverterUtils.convertStringToInt(receiveMoney()));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return makeMoney();
        }
    }

    private String receiveMoney() {
        return InputView.inputMoney();
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
        for (int idx = 0; idx < manualCount.getNumber(); idx++) {
            lottos.add(new Lotto(InputView.inputManualLottos()));
            lottoGame.makeManualLottos(lottos, receiveManualLotto());
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
            return new WinningLotto(InputView.inputWinningLotto(),
                    ConverterUtils.convertStringToInt(InputView.inputBonusBall()));
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
