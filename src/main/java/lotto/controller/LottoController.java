package lotto.controller;

import lotto.model.LottoGame;
import lotto.model.Lottos;
import lotto.view.InputView;
import lotto.view.ResultView;

import java.util.List;

public class LottoController {
    public static void runGame() {
        Integer money = InputView.insertMoney();
        Lottos lottos = LottoGame.buyLottos(money);
        ResultView.printGeneratedLottos(lottos.getLottos());

        List<Integer> winningNumbers = InputView.inputWinningNumbers();
        Integer bonusNumber = InputView.inputBonusNumber();
        LottoGame lottoGame = new LottoGame(winningNumbers, bonusNumber);

        ResultView.printResultStatistics(lottoGame.generateLottoResult(lottos));
        ResultView.printYield(lottoGame.calculateYield(money));
    }
}
