package view;

import constant.ErrorMessage;
import constant.OutputMessage;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import model.LottoNumbers;

public class WinLottoView {
    private static final String DELIMITER = ",";

    public void printWinNumberGuide() {
        System.out.println(OutputMessage.WIN_NUMBERS);
    }

    public List<Integer> readWinNumbers() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        List<String> splitInput = Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .toList();
        validateNumberCount(splitInput);
        splitInput.forEach(this::validatePositiveNumber);
        List<Integer> winNumbers = splitInput.stream().mapToInt(this::validatePositiveNumber).boxed().toList();
        winNumbers.forEach(this::validateBound);
        validateDuplicate(winNumbers);
        return winNumbers;
    }

    public void printBonusNumberGuide() {
        System.out.println(OutputMessage.BONUS_NUMBER);
    }

    public Integer readBonusNumber() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Integer bonusNumber = validatePositiveNumber(input);
        validateBound(bonusNumber);
        return bonusNumber;
    }

    private void validateNumberCount(List<String> winNumbers) {
        if (winNumbers.size() != LottoNumbers.LOTTO_NUMBER_COUNT) {
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
        if (input < LottoNumbers.MINIMUM_LOTTO_NUMBER || input > LottoNumbers.MAXIMUM_LOTTO_NUMBER) {
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
