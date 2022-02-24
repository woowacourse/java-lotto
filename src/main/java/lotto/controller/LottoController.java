package lotto.controller;

import lotto.model.money.Money;
import lotto.view.InputView;

public class LottoController {
    public void run() {
        Money money = Money.of(InputView.requestMoney());
    }
}
