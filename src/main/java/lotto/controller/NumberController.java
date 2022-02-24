package lotto.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.Number;
import lotto.view.InputView;

public class NumberController {

    public List<Number> getWinningNumbers() {
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
        }
        return null;
    }
}
