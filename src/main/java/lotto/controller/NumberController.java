package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        } while (!isValid || numbers.isEmpty());
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
            return new ArrayList<>();
        }
    }

    public Number getBonusNumber(List<Number> numbers) {
        Optional<Number> validNumber;

        do {
            validNumber = getValidNumber(numbers);
        } while (validNumber.isEmpty());

        return validNumber.get();
    }

    private Optional<Number> getValidNumber(List<Number> numbers) {
        try {
            Number number = new Number(InputView.inputBonusNumber());
            checkDuplicate(numbers, number);
            return Optional.of(number);
        } catch (IllegalArgumentException exception) {
            OutputView.printError(exception.getMessage());
            return Optional.empty();
        }
    }

    private void checkDuplicate(List<Number> numbers, Number number) {
        if (numbers.contains(number)) {
            throw new IllegalArgumentException(DUPLICATE_NUMBER_MESSAGE);
        }
    }
}
