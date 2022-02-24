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
        Lottos lottos = new Lottos(money);
        OutputView.printInitResult(lottos);

        List<Number> winningNumbers = numberController.getWinningNumbers();
        Number bonusNumber = numberController.getBonusNumber(winningNumbers);

        Result result = lottos.getResult(winningNumbers, bonusNumber);
        OutputView.printPlayResult(result, money);
    }
}
