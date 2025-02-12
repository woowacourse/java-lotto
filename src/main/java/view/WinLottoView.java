package view;

import constant.Constants;
import constant.ErrorMessage;
import constant.OutputMessage;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class WinLottoView {
    public void printWinNumberGuide() {
        System.out.println(OutputMessage.WIN_NUMBERS);
    }

    public void readWinNumbers() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        List<String> winNumbers = Arrays.stream(input.split(Constants.DELIMITER))
                .map(String::trim)
                .toList();
        validateNumberCount(winNumbers);
        winNumbers.forEach(this::validatePositiveNumber);
        List<Integer> numbers = winNumbers.stream().mapToInt(this::validatePositiveNumber).boxed().toList();
        numbers.forEach(this::validateBound);
        validateDuplicate(numbers);
    }

    public void printBonusNumberGuide() {
        System.out.println(OutputMessage.BONUS_NUMBER);
    }

    public void readBonusNumber() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Integer bonusNumber = validatePositiveNumber(input);
        validateBound(bonusNumber);
    }


    private void validateNumberCount(List<String> winNumbers) {
        if (winNumbers.size() != Constants.LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_COUNT_EXCEPTION);
        }
    }

    private Integer validatePositiveNumber(String input) {
        String POSITIVE_INTEGER_REGEX = "[1-9]\\d*";
        if (!input.matches(POSITIVE_INTEGER_REGEX)) {
            throw new IllegalArgumentException(ErrorMessage.POSITIVE_NUMBER_EXCEPTION);
        }
        return Integer.parseInt(input);
    }

    private void validateBound(Integer input) {
        if (input < Constants.MINIMUM_LOTTO_NUMBER || input > Constants.MAXIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_BOUND_EXCEPTION);
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> numberSet = new HashSet<>(numbers);
        if (numbers.size() != numberSet.size()) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_DUPLICATE_EXCEPTION);
        }
    }
}
