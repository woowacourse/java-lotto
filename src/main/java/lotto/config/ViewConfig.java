package lotto.config;

import lotto.view.ErrorView;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ViewConfig {
    public static InputView getInputView() {
        return InputView.getInstance();
    }

    public static OutputView getOutputView() {
        return OutputView.getInstance();
    }

    public static ErrorView getErrorView() {
        return ErrorView.getInstance();
    }
}
