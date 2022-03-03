package lotto.config;

import lotto.controller.AutoPurchaseController;
import lotto.controller.InputWinningController;

public class ControllerConfig {
    public static AutoPurchaseController getAutoPurchaseController() {
        return AutoPurchaseController.getInstance();
    }

    public static InputWinningController getInputWinningController() {
        return InputWinningController.getInstance();
    }
}
