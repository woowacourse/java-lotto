package lotto;

import java.util.Arrays;
import lotto.controller.LottoController;
import lotto.view.OutputView;
import lotto.view.Screen;

public class Application {

    public static void main(String[] args) {

        String money = Screen.getInputMoney();
        LottoController lottoController = new LottoController(money);
        lottoController.run();

    }
}
