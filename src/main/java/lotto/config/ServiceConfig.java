package lotto.config;

import lotto.service.PurchaseService;
import lotto.service.MoneyService;
import lotto.service.WinningService;

public class ServiceConfig {

    public static PurchaseService getPurchaseService() {
        return PurchaseService.getInstance();
    }

    public static WinningService getWinningService() {
        return WinningService.getInstance();
    }

    public static MoneyService getMoneyService() {
        return MoneyService.getInstance();
    }
    
}
