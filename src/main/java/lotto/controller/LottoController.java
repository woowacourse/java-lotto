package lotto.controller;

import lotto.model.Lotto;
import lotto.model.LottoGame;
import lotto.model.ResultMap;
import lotto.view.InputView;
import lotto.view.ResultView;

import java.util.List;

public class LottoController {
    public static void runGame() {
        Integer money = InputView.insertMoney();
        List<Lotto> lottos = LottoGame.buyLottos(money);
        ResultView.printGeneratedLottos(lottos);

        List<Integer> winningNumbers = InputView.inputWinningNumbers();
        Integer bonusNumber = InputView.inputBonusNumber();
        LottoGame lottoGame = new LottoGame(winningNumbers, bonusNumber);

        ResultView.printResultStatistics(lottoGame.getWinningResult(lottos));
        ResultView.printYield(lottoGame.calculateYield(money, lottos));
    }
}
