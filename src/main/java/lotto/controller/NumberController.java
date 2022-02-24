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
        boolean isValid;
        List<Number> numbers;

        do {
            List<Integer> integers = StringUtil.toIntegers(InputView.inputWinningNumbers());
            isValid = validateNumbers(integers);
            numbers = toNumbers(integers);
        } while (!isValid || numbers == null);
        return numbers;
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
        try {
            return integers.stream()
                    .map(Number::new)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException exception) {
            OutputView.printError(exception.getMessage());
            return null;
        }
    }

    public Number getBonusNumber(List<Number> numbers) {
        Number validNumber;

        do {
            validNumber = getValidNumber(numbers);
        } while (validNumber == null);

        return validNumber;
    }

    private Number getValidNumber(List<Number> numbers) {
        try {
            Number number = new Number(InputView.inputBonusNumber());
            checkDuplicate(numbers, number);
            return number;
        } catch (IllegalArgumentException exception) {
            OutputView.printError(exception.getMessage());
            return null;
        }
    }

    private void checkDuplicate(List<Number> numbers, Number number) {
        if (numbers.contains(number)) {
            throw new IllegalArgumentException(DUPLICATE_NUMBER_MESSAGE);
        }
    }
}
