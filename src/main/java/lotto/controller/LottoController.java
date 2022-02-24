package lotto.controller;

import java.util.List;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Number;
import lotto.domain.Result;
import lotto.view.OutputView;

public class LottoController {

    private final MoneyController moneyController;
    private final NumberController numberController;

    public LottoController() {
        this.moneyController = new MoneyController();
        this.numberController = new NumberController();
    }

    public void play() {
        Money money = moneyController.getBuyMoney();

        int amount = money.getValue() / 1000;
        OutputView.printLottoCount(amount);

        Lottos lottos = new Lottos(amount);
        OutputView.printLottos(lottos);

        List<Number> winningNumbers = numberController.getWinningNumbers();
        Number bonusNumber = numberController.getBonusNumber(winningNumbers);

        Result result = lottos.getResult(winningNumbers, bonusNumber);
        OutputView.printResult(result);

        double rateOfProfit = result.getRateOfProfit(money);
        OutputView.printRateOfProfit(rateOfProfit);
    }
}
