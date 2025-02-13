package validator;

import java.util.List;
import java.util.stream.Stream;

public class LottoValidator {
    public static void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("");
        }
        if (hasDistinctNumber(numbers)) {
            throw new IllegalArgumentException("");
        }
        if (numbers.stream().anyMatch(number -> !isValidNumber(number))) {
            throw new IllegalArgumentException("");
        }
        if (!isSorted(numbers)) {
            throw new IllegalArgumentException("");
        }
    }

    private static boolean isSorted(List<Integer> numbers) {
        Stream<Integer> sortedNumbers = numbers.stream().sorted();
        return sortedNumbers.equals(numbers);
    }

    private static boolean isValidNumber(int number) {
        return number > 0 && number <= 45;
    }

    private static boolean hasDistinctNumber(List<Integer> numbers) {
        int distinctedSize = numbers.stream()
                .distinct()
                .toList()
                .size();

        return numbers.size() != distinctedSize;
    }
}
