package lotto.config;

import lotto.controller.AutoPurchaseController;
import lotto.controller.InputWinningController;
import lotto.controller.ManualPurchaseController;
import lotto.controller.MoneyController;

public class ControllerConfig {

    public static MoneyController getMoneyController() {
        return MoneyController.getInstance();
    }

    public static ManualPurchaseController getManualPurchaseController() {
        return ManualPurchaseController.getInstance();
    }

    public static AutoPurchaseController getAutoPurchaseController() {
        return AutoPurchaseController.getInstance();
    }

    public static InputWinningController getInputWinningController() {
        return InputWinningController.getInstance();
    }
}
