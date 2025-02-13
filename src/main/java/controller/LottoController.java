package controller;

import domain.Buyer;
import domain.LottoMatch;
import domain.Money;
import domain.WinningLotto;
import java.util.HashMap;
import util.InputParser;
import util.Validator;
import view.InputView;
import view.OutputView;

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

        Money money = new Money(InputParser.parseStringToInteger(inputMoney));
        Buyer buyer = new Buyer(money);
        buyer.createLottos();
        String result = buyer.createResult();
        outputView.displayLottos(money.calculateTotalLotto(), result);

        String inputWinningNumber = inputView.inputWinningNumbers();
        Validator.inputValidatorIsNull(inputWinningNumber);

        String inputBonusNumber = inputView.inputBonusNumber();
        Validator.inputValidatorIsNull(inputBonusNumber);

        WinningLotto winningLotto = new WinningLotto(InputParser.parseStringToList(inputWinningNumber),
                InputParser.parseStringToInteger(inputBonusNumber));
        HashMap<LottoMatch,Integer> lottoResult = buyer.countLottos(winningLotto);

        outputView.displayResult(lottoResult);

        double profit = money.calculateProfit(lottoResult);

        outputView.displayProfit(profit);
    }
}
