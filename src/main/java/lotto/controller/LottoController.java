package lotto.controller;

import static lotto.view.InputView.*;
import static lotto.view.ResultView.*;

import lotto.model.LottoGame;
import lotto.model.LottoResult;
import lotto.model.numbergenerator.ShuffleGenerator;

public class LottoController {
    public void runGame() {
        LottoGame lottoGame = new LottoGame(insertMoney(), new ShuffleGenerator());
        int numberOfManualLottos = inputNumberOfManualLottos();
        printGeneratedLottos(lottoGame.getLottos());
        LottoResult lottoResult = lottoGame.generateLottoResult(inputWinningNumbers(), inputBonusNumber());
        printResultStatistics(lottoResult);
        printYield(lottoGame.calculateYield(lottoResult));
    }
}
