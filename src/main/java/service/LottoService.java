package service;

import domain.Buyer;
import domain.LottoMatch;
import domain.Money;
import domain.WinningLotto;
import generator.RandomGenerator;
import java.util.Map;
import util.parser.InputParser;
import util.validator.Validator;

public class LottoService {

    private final RandomGenerator randomGenerator;

    public LottoService(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    public Money createMoney(String inputMoney) {
        Validator.inputValidatorIsNull(inputMoney);
        int moneyValue = InputParser.parseStringToInteger(inputMoney);
        return new Money(moneyValue);
    }

    public Buyer buyLotto(Money money) {
        return new Buyer(money, randomGenerator);
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

