package lotto;

import lotto.controller.LottoController;
import lotto.controller.MoneyController;
import lotto.domain.LottoWinningNumbers;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.dto.Result;

public class Application {

    public static void main(final String... args) {
        MoneyController moneyController = new MoneyController();
        LottoController lottoController = new LottoController();

        Money money = moneyController.inputMoney();
        Lottos lottos = lottoController.createLottos(money.purchasedLottoAmount());
        lottoController.printLottos(lottos);
        LottoWinningNumbers winningNumbers = lottoController.createLottoWinningNumbers();

        Result result = lottoController.calculateResult(winningNumbers, lottos);
        lottoController.printWinningResult(result);

        double profit = moneyController.calculateProfit(result, money);
        moneyController.printProfit(profit);
    }
}
