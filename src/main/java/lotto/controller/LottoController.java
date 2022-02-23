package lotto.controller;

import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private InputView inputView;
    private OutputView outputView;
    private Money money;
    private Lottos lottos;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void start() {
        money = getMoney();
        lottos=new Lottos(money);
    }

    private Money getMoney() {
        try {
            outputView.printAskMoneyInputMessage();
            Money money = new Money(inputView.getMoneyInput());
            return money;
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return getMoney();
        }
    }
}
