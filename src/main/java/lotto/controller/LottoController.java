package lotto.controller;

import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Result;
import lotto.domain.WinningNumbers;
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

        WinningNumbers winningNumbers = numberController.getWinningNumbers();
        numberController.getBonusNumber(winningNumbers);

        Result result = lottos.getResult(winningNumbers);
        OutputView.printPlayResult(result, money);
    }
}
