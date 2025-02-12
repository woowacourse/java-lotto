package controller;

import domain.Buyer;
import domain.Lotto;
import domain.Money;
import util.Validator;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {
    private InputView inputView;
    private OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        String inputMoney = inputView.inputLottoMoney();
        Validator.inputValidatorIsNull(inputMoney);
        Validator.inputValidatorParseInt(inputMoney);

        Money money = new Money(Integer.parseInt(inputMoney));
        Buyer buyer = new Buyer(money);
        buyer.createLottos();
        String result = buyer.createResult();
        outputView.displayLottos(result);
    }
}
