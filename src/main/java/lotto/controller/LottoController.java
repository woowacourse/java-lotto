package lotto.controller;

import static lotto.view.InputView.*;
import static lotto.view.ResultView.*;

import lotto.model.LottoGame;
import lotto.model.LottoResult;
import lotto.model.Lottos;
import lotto.model.numbergenerator.ShuffleGenerator;

public class LottoController {
    public void runGame() {
        long lottoMoney = insertMoney();
        int numberOfManualLottos = inputNumberOfManualLottos();
        LottoGame lottoGame = new LottoGame(lottoMoney, numberOfManualLottos, new ShuffleGenerator());

        Lottos manualLottos = lottoGame.buyManualLottos(inputManualLottos(numberOfManualLottos));
        printGeneratedLottos(manualLottos.getLottos(), lottoGame.getAutoLottos());

        LottoResult lottoResult = lottoGame.generateLottoResult(manualLottos, inputWinningNumbers(),
            inputBonusNumber());
        printResultStatistics(lottoResult);
        printYield(lottoGame.calculateYield(lottoResult));
    }
}
