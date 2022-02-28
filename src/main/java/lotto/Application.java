package lotto;

import lotto.controller.LottoController;
import lotto.controller.MoneyController;
import lotto.controller.WinningNumbersController;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.WinningNumbers;

public class Application {
    public static void main(String[] args) {
        final MoneyController moneyController = new MoneyController();
        final LottoController lottoController = new LottoController();
        final WinningNumbersController winningNumbersController = new WinningNumbersController();

        final Money money = moneyController.getInputMoney();
        final Lottos lottos = lottoController.initLottos(money);
        final WinningNumbers winningNumbers = winningNumbersController.getWinningNumbers();

        lottoController.play(lottos, winningNumbers, money);
    }
}
