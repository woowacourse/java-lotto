package lotto.vaildator;

import lotto.costant.ExceptionMessage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LogicValidator {
    public static void validateDuplication(List<Integer> numbers) {
        Set<Integer> notDuplicatedNumbers = new HashSet<>(numbers);
        if (notDuplicatedNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATED_NUMBERS.getContent());
        }
    }

    public static void validateRange(List<Integer> numbers, int min, int max) {
        boolean isInRange = numbers.stream().allMatch(number -> number >= min && number <= max);
        if (!isInRange) {
            throw new IllegalArgumentException(ExceptionMessage.OUT_OF_RANGE.getContent());
        }
    }

    public static void validateSize(List<Integer> numbers, int size) {
        if (numbers.size() != size) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_NUMBER_COUNT.getContent());
        }
    }
}
