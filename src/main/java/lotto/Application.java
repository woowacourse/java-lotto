package lotto;

import lotto.controller.LottoController;
import lotto.view.InputView;

public class Application {

    public static void main(String[] args) {
        String money = InputView.getInputMoney();
        LottoController lottoController = new LottoController(money);
        lottoController.run();
    }
}
