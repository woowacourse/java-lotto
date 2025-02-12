package lotto;

import lotto.controller.LottoController;
import lotto.view.InputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        LottoController lottoController = new LottoController(inputView);
        lottoController.run();
    }
}
