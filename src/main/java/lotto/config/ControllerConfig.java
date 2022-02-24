package lotto.config;

import lotto.controller.AutoPurchaseController;
import lotto.controller.InputWinningController;
import lotto.controller.PurchaseController;

public class ControllerConfig {
    public static PurchaseController getPurchaseController() {
        return new AutoPurchaseController(ServiceConfig.getPurchaseService(), ServiceConfig.getMoneyService());
    }

    public static InputWinningController getWinningController() {
        return new InputWinningController(ServiceConfig.getWinningService(), ServiceConfig.getMoneyService());
    }
}
