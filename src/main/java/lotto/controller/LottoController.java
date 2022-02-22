package lotto.controller;

import lotto.domain.Money;
import lotto.view.InputView;

public class LottoController {

    public void run() {
        Money purchaseMoney = new Money(InputView.requestPurchaseMoney());
    }

}
