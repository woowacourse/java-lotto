package lotto;

import java.util.List;
import lotto.controller.LottoController;
import lotto.view.InputView;
import lotto.view.ResultView;

public class Application {

    public static void main(String[] args) {
        int money = InputView.requestPurchaseMoney();
        LottoController lottoController = new LottoController(money);

        int manualCount = InputView.requestManualCount(money);
        List<List<Integer>> manualNumbers = InputView.requestManualNumbers(manualCount);
        ResultView.printLottos(lottoController.purchase(manualNumbers), manualCount);

        List<Integer> winningNumbers = InputView.requestWinningNumber();
        int bonusNumber = InputView.requestBonusNumber();
        ResultView.printResults(lottoController.requestLottoResults(winningNumbers, bonusNumber));
    }
}
