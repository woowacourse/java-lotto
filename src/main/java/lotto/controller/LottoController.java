package lotto.controller;

import static lotto.view.InputView.*;
import static lotto.view.ResultView.*;

import lotto.model.LottoGame;
import lotto.model.LottoResult;
import lotto.model.lottofactory.AutoLottoFactory;

public class LottoController {
    public void runGame() {
        long lottoMoney = insertMoney();
        int numberOfManualLottos = inputNumberOfManualLottos();
        LottoGame lottoGame = new LottoGame(lottoMoney, numberOfManualLottos);

        lottoGame.buyLottos(inputManualLottos(numberOfManualLottos), new AutoLottoFactory());
        printGeneratedLottos(lottoGame.getLottos());

        LottoResult lottoResult = lottoGame.generateLottoResult(inputWinningNumbers(),
            inputBonusNumber());
        printResultStatistics(lottoResult);
        printYield(lottoGame.calculateYield(lottoResult));
    }
}
