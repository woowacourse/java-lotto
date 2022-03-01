package lotto;

import lotto.controller.LottoController;
import lotto.view.InputView;
import lotto.view.ResultView;

public class Application {

    public static void main(String[] args) {
        LottoController lottoController = new LottoController();
        int money = InputView.requestPurchaseMoney();
        lottoController.inputMoney(money);

        int manualCount = InputView.requestManualCount(money);
        ResultView.printLottos(lottoController.purchase(InputView.requestManualNumbers(manualCount)), manualCount);
        ResultView.printResults(
                lottoController.requestLottoResults(InputView.requestWinningNumber(), InputView.requestBonusNumber()));
    }
}
