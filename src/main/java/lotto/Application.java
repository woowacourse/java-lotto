package lotto;

import lotto.controller.LottoController;
import lotto.controller.MoneyController;
import lotto.domain.lotto.LottoWinningNumbers;
import lotto.domain.lotto.Lottos;
import lotto.domain.result.LottoResult;
import lotto.domain.user.Money;

public class Application {

    public static void main(final String... args) {
        MoneyController moneyController = new MoneyController();
        LottoController lottoController = new LottoController();

        Money money = moneyController.createMoney();
        Lottos lottos = lottoController.inputLottoMoney(money.getMoney());
        lottoController.printLottos(lottos);
        LottoWinningNumbers lottoWinningNumbers = lottoController.createLottoWinningNumbers();

        LottoResult lottoResult = lottoController.calculateRanks(lottos, lottoWinningNumbers);
        lottoController.printWinningResult(lottoResult);

        double profit = moneyController.calculateProfit(money, lottoResult);
        moneyController.printProfit(profit);
    }
}
