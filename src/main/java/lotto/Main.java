package lotto;

import lotto.controller.LottoController;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Main {

    public static void main(String[] args) {
        LottoController lottoController = initializeController();
        lottoController.start();
    }

    private static LottoController initializeController() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        return new LottoController(inputView, outputView);
    }
}
