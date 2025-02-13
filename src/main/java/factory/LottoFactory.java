package factory;

import controller.LottoController;
import view.input.BasicInputParser;
import view.input.ConsoleInputView;
import view.input.InputView;
import view.output.ConsoleOutputView;

public abstract class LottoFactory {
    public static LottoController create() {
        return new LottoController(createConsoleInputView(), new ConsoleOutputView());
    }

    private static InputView createConsoleInputView() {
        return new ConsoleInputView(new BasicInputParser());
    }

}
