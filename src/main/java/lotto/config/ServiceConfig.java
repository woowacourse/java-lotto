package lotto.config;

import lotto.service.MoneyService;
import lotto.service.PurchaseService;
import lotto.service.WinningService;

public class ServiceConfig {
    public static PurchaseService getPurchaseService() {
        return new PurchaseService(RepositoryConfig.getLottoRepository());
    }

    public static WinningService getWinningService() {
        return new WinningService(RepositoryConfig.getLottoRepository());
    }

    public static MoneyService getMoneyService() {
        return new MoneyService(RepositoryConfig.getMoneyRepository());
    }
}
