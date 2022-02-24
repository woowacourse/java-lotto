package lotto.controller;

import java.util.ArrayList;
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
        Lottos lottos = buyLottos(money);
        // 지난 주 당첨 번호 입력
        // getLastWeekWinningNumbers();
        List<Number> lastWeekWinningNumbers = getLastWeekWinningNumbers();
        // 보너스 번호 입력
        Number bonusNumber = getBonusNumber(lastWeekWinningNumbers);
        // 결과 출력
        // Result result = resultOfLottos(lastWeekWinningNumbers, bonusNumber, lottos);
        Result result = lottos.getResult(lastWeekWinningNumbers, bonusNumber);
        OutputView.printResult(result);
        // 수익률 출력
        // getRateOfProfit(result, money);
        double rateOfProfit = result.getRateOfProfit(money);
        OutputView.printRateOfProfit(rateOfProfit);
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

    private Lottos buyLottos(Money money) {
        Lottos lottos = new Lottos(money);
        OutputView.printLottoCount(lottos.getCount());
        OutputView.printLottos(lottos);
        return lottos;
    }

    private Number getBonusNumber(List<Number> numbers) {
        boolean isDuplicate = false;
        Number number;

        do {
            String input = InputView.inputBonusNumber();
            number = toNumber(input);
            isDuplicate = numbers.contains(number);
            if (isDuplicate) {
                System.out.println("중복입니다");
            }
        } while (number == null || isDuplicate);

        return number;
    }

    private Number toNumber(String input) {
        try {
            return new Number(input);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

    private List<Number> getLastWeekWinningNumbers() {
        boolean isValid = false;
        List<Integer> numbers;

        do {
            String input = InputView.inputLastWeekWinningNumbers();
            numbers = toList(input.split(", "));
            isValid = validateNumbers(numbers);
        } while (!isValid);

        return numbers.stream()
            .map(String::valueOf)
            .map(Number::new)
            .collect(Collectors.toList());
    }

    private List<Integer> toList(String[] splitInput) {
        List<Integer> numbers = new ArrayList<>();
        try {
            numbers = Arrays.stream(splitInput)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        } catch (NumberFormatException exception) {
        }
        return numbers;
    }

    private boolean validateNumbers(List<Integer> numbers) {
        try {
            Lotto.validateNumbers(numbers);
            return true;
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return false;
        }
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
