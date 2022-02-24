package lotto.config;

import lotto.shop.LottoShop;
import lotto.shop.Shop;

public class AppConfig {

    public static Shop getShop() {
        return new LottoShop(ViewConfig.getInputView(), ViewConfig.getOutputView(), ViewConfig.getErrorView(),
            ControllerConfig.getPurchaseController(), ControllerConfig.getWinningController());
    }
}
