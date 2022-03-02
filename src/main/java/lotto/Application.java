package lotto;

import lotto.controller.LottoController;
import lotto.view.InputView;
import lotto.view.ResultView;

public class Application {

    public static void main(String[] args) {
        int money = InputView.requestPurchaseMoney();
        LottoController lottoController = new LottoController(money);

        int manualCount = InputView.requestManualCount(money);
        ResultView.printLottos(lottoController.purchase(InputView.requestManualNumbers(manualCount)), manualCount);
        ResultView.printResults(
                lottoController.requestLottoResults(InputView.requestWinningNumber(), InputView.requestBonusNumber()));
    }
}
