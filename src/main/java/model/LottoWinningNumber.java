package model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoWinningNumber {
    private static final String REGEX_NUMBER = "[0-9]+";
    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;

    private List<Integer> winningNumbers;

    public LottoWinningNumber(String numbers) {
        validateInputNumbersBlank(numbers);
        validateNumbersConsistOfInt(numbers);
        validateNumberOutOfRange(numbers);
        this.winningNumbers = makeWinningNumbers(split(numbers));
    }

    private void validateInputNumbersBlank(String number) {
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException();
        }
    }

    private List<String> split(String numbers) {
        return Arrays.asList(numbers.replace(" ", "").split(","));
    }

    private void validateNumbersConsistOfInt(String numbers) {
        String joinNumbers = String.join("", split(numbers));

        if (!joinNumbers.matches(REGEX_NUMBER)) {
            throw new IllegalArgumentException();
        }
    }

    private void validateNumberOutOfRange(String numbers) {
        long count = split(numbers).stream()
                .filter(number -> Integer.parseInt(number) < LOTTO_MIN_NUMBER || Integer.parseInt(number) > LOTTO_MAX_NUMBER)
                .count();

        if (count > 0) {
            throw new IllegalArgumentException();
        }
    }

    private List<Integer> makeWinningNumbers(List<String> numbers) {
        return numbers.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }
}
