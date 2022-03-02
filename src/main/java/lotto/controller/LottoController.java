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
        Lottos lottos = LottoGame.buyLottos(lottoMoney, new ShuffleGenerator());
        ResultView.printGeneratedLottos(lottos.getLottos());

        LottoGame lottoGame = new LottoGame(InputView.inputWinningNumbers(), InputView.inputBonusNumber());

        LottoResult lottoResult = lottoGame.generateLottoResult(lottos);
        ResultView.printResultStatistics(lottoResult);
        ResultView.printYield(lottoGame.calculateYield(lottoMoney, lottoResult));
    }
}
