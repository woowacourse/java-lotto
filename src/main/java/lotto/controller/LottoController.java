package lotto.controller;

import lotto.domain.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    public void play() {
        Money money = generateMoney();
    }

    private Money generateMoney() {
        try {
            return new Money(InputView.inputMoney());
        }catch (RuntimeException e){
            OutputView.printErrorMessage(e.getMessage());
            return generateMoney();
        }
    }

}
