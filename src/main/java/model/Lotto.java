package model;

import static constant.ExceptionMessage.INVALID_INPUT_NULL_OR_BLANK;
import static constant.ExceptionMessage.INVALID_LOTTO_RANGE;
import static constant.ExceptionMessage.INVALID_LOTTO_SIZE;
import static constant.ExceptionMessage.INVALID_LOTTO_FORMAT;
import static constant.ExceptionMessage.DUPLICATE_LOTTO_NUMBER;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Lotto {

    private static final String SEPARATOR = ", ";
    private static final Integer LOTTO_SIZE = 6;
    private static final Integer LOTTO_MIN_RANGE = 1;
    private static final Integer LOTTO_MAX_RANGE = 45;

    private final List<Integer> numbers;

    public static Lotto of(final String input) { // "1, 2, 3, 4, 5, 6"
        validateNullOrBlank(input);
        String[] splitInputs = input.split(SEPARATOR);
        List<String> parsedInputs = List.of(splitInputs);

        List<Integer> numbers = new ArrayList<>();
        parsedInputs.forEach(parsedInput -> {
            validateInteger(parsedInput);
            numbers.add(Integer.parseInt(parsedInput));
        });

        validateSize(numbers);
        return new Lotto(numbers);
    }

    public Lotto(final List<Integer> numbers) {
        validateRange(numbers);
        validateDuplicate(numbers);
        this.numbers = numbers;
    }

    private static void validateNullOrBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(INVALID_INPUT_NULL_OR_BLANK.getMessage());
        }
    }

    private static void validateSize(final List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(INVALID_LOTTO_SIZE.getMessage(LOTTO_SIZE));
        }
    }

    private void validateRange(final List<Integer> inputs) {
        inputs.stream()
                .filter(input -> LOTTO_MIN_RANGE > input || input > LOTTO_MAX_RANGE)
                .forEach(input -> {
                    throw new IllegalArgumentException(
                            INVALID_LOTTO_RANGE.getMessage(LOTTO_MIN_RANGE, LOTTO_MAX_RANGE));
                });
    }

    private void validateDuplicate(final List<Integer> inputs) {
        HashSet<Integer> set = new HashSet<>(inputs);
        if (inputs.size() != set.size()) {
            throw new IllegalArgumentException(DUPLICATE_LOTTO_NUMBER.getMessage());
        }
    }

    private static void validateInteger(final String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_LOTTO_FORMAT.getMessage());
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
