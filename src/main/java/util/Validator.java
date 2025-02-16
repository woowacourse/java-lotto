package util;

import static constant.pattern.InputPattern.INTEGER_PATTERN;

import java.util.HashSet;
import java.util.List;
import model.Lotto;

public class Validator {

    private Validator() {
    }

    public static void validateNullOrBlank(final String input, final String errorMessage) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validateInteger(final String input, final String errorMessage) {
        if (!input.matches(INTEGER_PATTERN.getContent())) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validateRange(final int input, final int minRange,
                                     final int maxRange, final String errorMessage) {
        if (minRange > input || input > maxRange) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validateDuplicateLottoAndBonus(final Lotto lotto, final int bonus, final String errorMessage) {
        if (lotto.contains(bonus)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validateMinimum(final int number, final int minimum, final String errorMessage) {
        if (number < minimum) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validateUnit(final int number, final int unit, final String errorMessage) {
        if (number % unit != 0) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validateSize(final List<Integer> inputs, final int size, final String errorMessage) {
        if (inputs.size() != size) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validateDuplicate(final List<Integer> inputs, final String errorMessage) {
        if (new HashSet<>(inputs).size() != inputs.size()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
