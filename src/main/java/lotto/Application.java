package lotto;

import lotto.config.AppConfig;
import lotto.shop.Shop;

public class Application {
    public static void main(String[] args) {
        Shop shop = AppConfig.getShop();
        shop.operate();
    }
}
