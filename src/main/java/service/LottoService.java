package service;

import domain.Buyer;
import domain.LottoMatch;
import domain.Money;
import domain.WinningLotto;
import util.parser.InputParser;
import util.validator.Validator;

import java.util.Map;

public class LottoService {

    public Money createMoney(String inputMoney) {
        Validator.inputValidatorIsNull(inputMoney);
        int moneyValue = InputParser.parseStringToInteger(inputMoney);
        return new Money(moneyValue);
    }

    public Buyer buyLotto(Money money) {
        return new Buyer(money);
    }

    public WinningLotto createWinningLotto(String inputWinningNumbers, String inputBonusNumber) {
        Validator.inputValidatorIsNull(inputWinningNumbers);
        Validator.inputValidatorIsNull(inputBonusNumber);

        return new WinningLotto(
                InputParser.parseStringToList(inputWinningNumbers),
                InputParser.parseStringToInteger(inputBonusNumber)
        );
    }

    public Map<LottoMatch, Integer> calculateLottoResults(Buyer buyer, WinningLotto winningLotto) {
        return buyer.countLottos(winningLotto);
    }

    public double calculateProfit(Money money, Map<LottoMatch, Integer> lottoResult) {
        return money.calculateProfit(lottoResult);
    }
}
