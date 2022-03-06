package lotto;

import java.util.List;
import lotto.controller.LottoController;
import lotto.domain.LottoGame;
import lotto.domain.Money;
import lotto.utils.RandomLottoGenerateStrategy;
import lotto.view.InputView;
import lotto.view.ResultView;

public class Application {

    public static void main(String[] args) {
        int money = InputView.requestPurchaseMoney();
        LottoGame lottoGame = new LottoGame(new Money(money));
        LottoController lottoController = new LottoController();

        int manualCount = InputView.requestManualCount(money);
        List<List<Integer>> manualNumbers = InputView.requestManualNumbers(manualCount);
        ResultView.printLottos(lottoController.purchase(
                lottoGame, manualNumbers, new RandomLottoGenerateStrategy()), manualCount);

        List<Integer> winningNumbers = InputView.requestWinningNumber();
        int bonusNumber = InputView.requestBonusNumber();
        ResultView.printResults(lottoController.requestLottoResults(lottoGame, winningNumbers, bonusNumber));
    }
}
