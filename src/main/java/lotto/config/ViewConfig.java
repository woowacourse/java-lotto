package lotto.config;

import java.util.Scanner;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ViewConfig {

    public static InputView getInputView() {
        return new InputView(new Scanner(System.in));
    }

    public static OutputView getOutputView() {
        return new OutputView();
    }
}
