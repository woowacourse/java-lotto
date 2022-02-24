package lotto;

import lotto.controller.LottoController;
import lotto.controller.MoneyController;

public class Application {

    public static void main(final String... args) {
        MoneyController moneyController = new MoneyController();
        LottoController lottoController = new LottoController();

        lottoController.inputLottoMoney(moneyController.getMoney());
        lottoController.printLottos();
        lottoController.createLottoWinningNumbers();

        lottoController.calculateRanks();
        lottoController.printWinningResult();

        double profit = lottoController.calculateProfit(moneyController.getMoney());
        lottoController.printProfit(profit);
    }
}
