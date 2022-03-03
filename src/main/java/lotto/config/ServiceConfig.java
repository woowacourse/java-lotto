package lotto.config;

import lotto.service.AutoPurchaseService;
import lotto.service.MoneyService;
import lotto.service.WinningService;

public class ServiceConfig {
    public static AutoPurchaseService getPurchaseService() {
        return AutoPurchaseService.getInstance();
    }

    public static WinningService getWinningService() {
        return WinningService.getInstance();
    }

    public static MoneyService getMoneyService() {
        return MoneyService.getInstance();
    }
}
