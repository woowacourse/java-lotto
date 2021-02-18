package lotto.domain;

import lotto.exception.WinningNumberException;

import java.util.*;
import java.util.stream.Collectors;

public class Lotto {
    public static final String DELIMITER = ",";

    private final List<Integer> numbers;

    public Lotto() {
        numbers = new ArrayList<>(LottoGenerator.generateNumbers());
    }

    public Lotto(String inputNumbers) {
        NullCheckNumbers(inputNumbers);
        String[] splittedNumbers = inputNumbers.split(DELIMITER);
        List<Integer> winningNumbers = convertInputWinningNumbersToInteger(splittedNumbers);
        validateWinningNumbersCount(winningNumbers);
        this.numbers = new ArrayList<>(winningNumbers);
    }


    private static void validateWinningNumbersCount(List<Integer> winningNumbers) {
        if (new HashSet<>(winningNumbers).size() != 6) {
            throw new WinningNumberException();
        }
    }

    private static List<Integer> convertInputWinningNumbersToInteger(String[] splittedNumbers) {
        try {
            return Arrays.stream(splittedNumbers)
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new WinningNumberException();
        }
    }

    private static void NullCheckNumbers(String inputNumbers) {
        if (inputNumbers == null) {
            throw new WinningNumberException();
        }
    }


    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}