package lotto;

import lotto.controller.LottoController;
import lotto.model.LottoMachine;
import lotto.model.lotto.generator.DefaultNumberGenerator;
import lotto.view.input.ConsoleInputView;
import lotto.view.output.ConsoleOutputView;

public class Application {

    private static final LottoController lottoController = new LottoController(
            new ConsoleInputView(),
            new ConsoleOutputView(),
            new LottoMachine(new DefaultNumberGenerator())
    );

    public static void main(String[] args) {
        lottoController.run();
    }
}
