package lotto.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Number;
import lotto.domain.Result;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void play() {
        Money money = getBuyMoney();

        int amount = money.getValue() / 1000;
        OutputView.printLottoCount(amount);

        Lottos lottos = new Lottos(amount);
        OutputView.printLottos(lottos);

        List<Number> lastWeekWinningNumbers = getLastWeekWinningNumbers();
        Number bonusNumber = getBonusNumber();

        Result result = lottos.getResult(lastWeekWinningNumbers, bonusNumber);
        OutputView.printResult(result);

        double rateOfProfit = result.getRateOfProfit(money);
        OutputView.printRateOfProfit(rateOfProfit);
    }

    private Number getBonusNumber() {
        String input = InputView.inputBonusNumber();
        return new Number(input);
    }

    private List<Number> getLastWeekWinningNumbers() {
        String input = InputView.inputLastWeekWinningNumbers();
        String[] splitInput = input.split(", ");
        List<Integer> numbers = Arrays.stream(splitInput)
            .map(Integer::parseInt)
            .collect(Collectors.toList());
        Lotto.validateNumbers(numbers);
        return numbers.stream()
            .map(String::valueOf)
            .map(Number::new)
            .collect(Collectors.toList());
    }

    private Money getBuyMoney() {
        String input;
        Money money;

        do {
            input = InputView.inputMoney();
            money = getValidMoney(input);
        } while (money == null);
        return money;
    }

    private Money getValidMoney(String input) {
        Money money = null;
        try {
            money = new Money(input);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
        return money;
    }
}
