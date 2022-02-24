package lotto;

import lotto.domain.controller.LottoController;
import lotto.domain.generator.LottoRandomGenerator;
import lotto.view.input.ConsoleInputView;
import lotto.view.output.ConsoleOutputView;

public class Application {
    public static void main(final String[] args) {
        final LottoController lottoController = new LottoController(
                new LottoRandomGenerator(), new ConsoleInputView(), new ConsoleOutputView());
        lottoController.run();
    }
}
