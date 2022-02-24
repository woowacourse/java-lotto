package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.Number;
import lotto.utils.StringUtil;
import lotto.view.InputView;
import lotto.view.OutputView;

public class NumberController {

    private static final String DUPLICATE_NUMBER_MESSAGE = "중복입니다";

    public List<Number> getWinningNumbers() {
        boolean isValid = false;
        List<Integer> integers;

        do {
            integers = StringUtil.toIntegers(InputView.inputWinningNumbers());
            isValid = validateNumbers(integers);
        } while (!isValid);
        return toNumbers(integers);
    }

    private boolean validateNumbers(List<Integer> numbers) {
        try {
            Lotto.validateNumbers(numbers);
            return true;
        } catch (IllegalArgumentException exception) {
            OutputView.printError(exception.getMessage());
            return false;
        }
    }

    private List<Number> toNumbers(List<Integer> integers) {
        return integers.stream()
                .map(String::valueOf)
                .map(Number::new)
                .collect(Collectors.toList());
    }

    public Number getBonusNumber(List<Number> numbers) {
        boolean isDuplicate = false;
        Number number;

        do {
            String input = InputView.inputBonusNumber();
            number = StringUtil.toNumber(input);
            isDuplicate = checkDuplicate(numbers, number);
        } while (number == null || isDuplicate);

        return number;
    }

    private boolean checkDuplicate(List<Number> numbers, Number number) {
        boolean isDuplicate = numbers.contains(number);
        if (isDuplicate) {
            OutputView.printError(DUPLICATE_NUMBER_MESSAGE);
        }
        return isDuplicate;
    }
}
