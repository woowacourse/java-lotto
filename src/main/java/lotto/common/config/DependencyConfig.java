package lotto.common.config;

import lotto.controller.Controller;
import lotto.domain.Calculator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class DependencyConfig {
    private Controller controller;

    public DependencyConfig() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        Calculator calculator = new Calculator();
        controller = new Controller(inputView, outputView, calculator);
    }

    public Controller getController() {
        return controller;
    }
}
