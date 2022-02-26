package lotto;

import lotto.controller.LottoController;
import lotto.console.LottoRunner;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

    public static void main(String[] args) {
        new LottoRunner(new InputView(), new OutputView(), new LottoController()).run();
    }
}
