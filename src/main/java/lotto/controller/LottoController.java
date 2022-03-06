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
        List<Lotto> manualLottos = sendManualLottos(lottoGame, manual);

        LottoStorage lottos = lottoGame.makeLottos(new LottoCount(money.getNumber(), manual.getNumber()), manualLottos);
        OutputView.printLottos(manual.getNumber(), lottos.getLottoStorage());
        WinningResult winnings = lottoGame.calcWinningNumber(lottos, new WinningLottoResponse(receiveWinningLotto()));
        sendResult(lottoGame, money.getNumber(), winnings);
    }

    private Money makeMoney() {
        try {
            String receivedMoney = InputView.inputMoney();
            return new Money(ConverterUtils.convertStringToInt(receivedMoney));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return makeMoney();
        }
    }

    private ManualCount receiveManualCount() {
        try {
            String receivedManualCount= InputView.inputManualLottoCount();
            return new ManualCount(ConverterUtils.convertStringToInt(receivedManualCount));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return receiveManualCount();
        }
    }

    private List<Lotto> sendManualLottos(LottoGame lottoGame, ManualCount manualCount) {
        InputView.inputManualLottoMessage();
        try {
            List<List<Integer>> lottos = receiveManualLottos(manualCount);
            return lottoGame.makeManualLottos(lottos);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return sendManualLottos(lottoGame, manualCount);
        }
    }

    private List<List<Integer>> receiveManualLottos(ManualCount manualCount) {
        List<List<Integer>> lottos = new ArrayList<>();
        for (int idx = 0; idx < manualCount.getNumber(); idx++) {
            List<Integer> receivedManualLottos = InputView.inputManualLottos();
            lottos.add(receivedManualLottos);
        }
        return lottos;
    }

    private WinningLotto receiveWinningLotto() {
        try {
            List<Integer> receivedWinningLotto = InputView.inputWinningLotto();
            String receivedBonusBall = InputView.inputBonusBall();
            return new WinningLotto(receivedWinningLotto, ConverterUtils.convertStringToInt(receivedBonusBall));
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
