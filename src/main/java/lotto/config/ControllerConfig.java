package lotto.config;

import lotto.controller.AutoPurchaseController;
import lotto.controller.InputWinningController;
import lotto.controller.PurchaseController;
import lotto.controller.WinningController;

public class ControllerConfig {
    public static PurchaseController getPurchaseController() {
        return AutoPurchaseController.getInstance();
    }

    public static WinningController getWinningController() {
        return InputWinningController.getInstance();
    }
}
