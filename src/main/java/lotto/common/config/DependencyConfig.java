package lotto.common.config;

import lotto.controller.Controller;
import lotto.controller.InputController;
import lotto.view.InputView;
import lotto.view.OutputView;

public class DependencyConfig {
    private Controller controller;

    public DependencyConfig() {
        InputView inputView = new InputView();
        InputController inputController = new InputController(inputView);
        OutputView outputView = new OutputView();
        controller = new Controller(inputController, outputView);
    }

    public Controller getController() {
        return controller;
    }
}
