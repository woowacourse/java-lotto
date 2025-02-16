package util;

import static constant.pattern.InputPattern.INTEGER_PATTERN;

import java.util.HashSet;
import java.util.List;
import model.Lotto;

public class Validator {

    private Validator() {
    }

    public static void validateNullOrBlank(String input, String errorMessage) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validateInteger(String input, String errorMessage) {
        if (!input.matches(INTEGER_PATTERN.getContent())) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validateRange(int input, int minRange, int maxRange, String errorMessage) {
        if (minRange > input || input > maxRange) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validateDuplicateLottoAndBonus(Lotto lotto, int bonus, String errorMessage) {
        if (lotto.contains(bonus)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validateMinimum(int number, int minimum, String errorMessage) {
        if (number < minimum) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validateUnit(int number, int unit, String errorMessage) {
        if (number % unit != 0) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validateSize(List<Integer> inputs, int size, String errorMessage) {
        if (inputs.size() != size) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validateDuplicate(List<Integer> inputs, String errorMessage) {
        if (new HashSet<>(inputs).size() != inputs.size()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
