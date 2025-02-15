package model;

import static constant.ExceptionMessage.INVALID_INPUT_NULL_OR_BLANK;
import static constant.ExceptionMessage.INVALID_LOTTO_RANGE;
import static constant.ExceptionMessage.INVALID_LOTTO_SIZE;
import static constant.ExceptionMessage.INVALID_LOTTO_FORMAT;
import static constant.ExceptionMessage.DUPLICATE_LOTTO_NUMBER;
import static constant.LottoConstant.LOTTO_NUMBER_MAX_RANGE;
import static constant.LottoConstant.LOTTO_NUMBER_MIN_RANGE;
import static constant.LottoConstant.LOTTO_SEPARATOR;
import static constant.LottoConstant.LOTTO_TICKET_SIZE;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Lotto {

    private final List<Integer> numbers;

    public static Lotto of(final String input) {
        validateNullOrBlank(input);
        List<Integer> numbers = parseLotto(input);
        return new Lotto(numbers);
    }

    public Lotto(final List<Integer> numbers) {
        validateSize(numbers);
        validateRange(numbers);
        validateDuplicate(numbers);
        this.numbers = numbers;
    }

    private static void validateNullOrBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(INVALID_INPUT_NULL_OR_BLANK.getMessage());
        }
    }

    private static List<Integer> parseLotto(String input) {
        return Arrays.stream(input.split(LOTTO_SEPARATOR))
                .map(String::trim)
                .map(Lotto::parseInteger)
                .toList();
    }

    private static int parseInteger(String input) {
        validateInteger(input);
        return Integer.parseInt(input);
    }

    private static void validateInteger(final String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_LOTTO_FORMAT.getMessage());
        }
    }

    private static void validateSize(final List<Integer> inputs) {
        if (inputs.size() != LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException(INVALID_LOTTO_SIZE.getMessage(LOTTO_TICKET_SIZE));
        }
    }

    private void validateRange(final List<Integer> inputs) {
        if (inputs.stream().anyMatch(num -> num < LOTTO_NUMBER_MIN_RANGE || num > LOTTO_NUMBER_MAX_RANGE)) {
            throw new IllegalArgumentException(
                    INVALID_LOTTO_RANGE.getMessage(LOTTO_NUMBER_MIN_RANGE, LOTTO_NUMBER_MAX_RANGE));
        }
    }

    private void validateDuplicate(final List<Integer> inputs) {
        if (new HashSet<>(inputs).size() != inputs.size()) {
            throw new IllegalArgumentException(DUPLICATE_LOTTO_NUMBER.getMessage());
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
