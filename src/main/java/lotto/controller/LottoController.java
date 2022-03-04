package lotto.controller;

import static lotto.view.InputView.*;
import static lotto.view.ResultView.*;

import java.util.List;

import lotto.model.LottoGame;
import lotto.model.LottoResult;
import lotto.model.Lottos;
import lotto.model.numbergenerator.ManualGenerator;
import lotto.model.numbergenerator.ShuffleGenerator;

public class LottoController {
    public void runGame() {
        int lottoMoney = insertMoney();
        int numberOfManualLottos = inputNumberOfManualLottos();
        LottoGame lottoGame = new LottoGame(lottoMoney, numberOfManualLottos, new ShuffleGenerator());

        List<List<Integer>> rawManualLottos = inputManualLottos(numberOfManualLottos);
        ManualGenerator manualGenerator = new ManualGenerator(rawManualLottos);
        Lottos manualLottos = new Lottos(manualGenerator, numberOfManualLottos);

        printGeneratedLottos(manualLottos.getLottos(), lottoGame.getLottos());
        LottoResult lottoResult = lottoGame.generateLottoResult(inputWinningNumbers(), inputBonusNumber());
        printResultStatistics(lottoResult);
        printYield(lottoGame.calculateYield(lottoResult));
    }
}
