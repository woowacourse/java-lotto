package lotto;

import lotto.controller.LottoController;
import lotto.view.input.ConsoleInputView;
import lotto.view.output.ConsoleOutputView;

public class Application {
    public static void main(final String[] args) {
        final int lottoPrice = 1000;
        final LottoController lottoController = new LottoController(
                new ConsoleInputView(), new ConsoleOutputView(), lottoPrice);
        lottoController.run();
    }
}
