package lotto.controller;

import lotto.model.LottoGame;
import lotto.model.LottoMoney;
import lotto.model.LottoResult;
import lotto.model.Lottos;
import lotto.model.ShuffleGenerator;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoController {
    public void runGame() {
        LottoMoney lottoMoney = new LottoMoney(InputView.insertMoney());
        LottoGame lottoGame = new LottoGame(lottoMoney, new ShuffleGenerator());
        ResultView.printGeneratedLottos(lottoGame.getLottos());

        LottoResult lottoResult = lottoGame.generateLottoResult(InputView.inputWinningNumbers(), InputView.inputBonusNumber());
        ResultView.printResultStatistics(lottoResult);
        ResultView.printYield(lottoGame.calculateYield(lottoMoney, lottoResult));
    }
}
