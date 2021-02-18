package lotto;

import lotto.controller.LottoController;
import lotto.view.Screen;

public class Application {

    public static void main(String[] args) {

        String money = Screen.getInputMoney();
        LottoController lottoController = new LottoController(money);
        lottoController.run();
    }
}
