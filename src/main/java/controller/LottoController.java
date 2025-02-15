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

  private final InputView inputView;
  private final OutputView outputView;

  public LottoController(InputView inputView, OutputView outputView) {
    this.inputView = inputView;
    this.outputView = outputView;
  }

  public void run() {
    Money money = creaeteMoney();

    Buyer buyer = new Buyer(money);
    outputView.displayLottos(money.calculateTotalLotto(), buyer.createResult());

    WinningLotto winningLotto = createWinningLotto();
    HashMap<LottoMatch, Integer> lottoResult = buyer.countLottos(winningLotto);
    outputView.displayResult(lottoResult);

    double profit = money.calculateProfit(lottoResult);
    outputView.displayProfit(profit);
  }

  private Money creaeteMoney() {
    String inputMoney = inputView.inputLottoMoney();
    Validator.inputValidatorIsNull(inputMoney);

    return new Money(InputParser.parseStringToInteger(inputMoney));
  }

  private WinningLotto createWinningLotto() {
    String inputWinningNumber = inputView.inputWinningNumbers();
    Validator.inputValidatorIsNull(inputWinningNumber);

    String inputBonusNumber = inputView.inputBonusNumber();
    Validator.inputValidatorIsNull(inputBonusNumber);

    return new WinningLotto(InputParser.parseStringToList(inputWinningNumber),
        InputParser.parseStringToInteger(inputBonusNumber));
  }
}
