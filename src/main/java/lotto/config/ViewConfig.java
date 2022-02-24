package lotto.config;

import lotto.view.ErrorView;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ViewConfig {
    public static InputView getInputView() {
        return new InputView(ClientConfig.getInputClient());
    }

    public static OutputView getOutputView() {
        return new OutputView(ClientConfig.getOutputClient());
    }

    public static ErrorView getErrorView() {
        return new ErrorView(ClientConfig.getErrorClient());
    }
}
