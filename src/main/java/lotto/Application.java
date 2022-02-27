package lotto;

import lotto.controller.LottoController;
import lotto.domain.strategy.AutoBuy;

public class Application {
    public static void main(String[] args) {
        AutoBuy autoBuy = new AutoBuy();
        LottoController lottoController = new LottoController(autoBuy);
        lottoController.start();
    }
}
