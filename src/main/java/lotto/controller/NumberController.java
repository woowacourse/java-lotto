package lotto.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.Number;
import lotto.utils.StringUtil;
import lotto.view.InputView;

public class NumberController {

    public List<Number> getWinningNumbers() {
        boolean isValid = false;
        List<Integer> integers;

        do {
            String input = InputView.inputLastWeekWinningNumbers();
            integers = StringUtil.toIntegers(input);
            isValid = validateNumbers(integers);
        } while (!isValid);
        return toNumbers(integers);
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
            return null;
        }
    }
}
