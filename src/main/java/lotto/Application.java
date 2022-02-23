package lotto;

import lotto.controller.LottoController;
import lotto.controller.MoneyController;

public class Application {
    public static void main(final String[] args) {
        MoneyController moneyController = new MoneyController();
        LottoController lottoController = new LottoController();

        lottoController.inputLottoMoney(moneyController.getMoney());
        lottoController.printLottos();
        lottoController.createLottoWinningNumbers();
    }
}