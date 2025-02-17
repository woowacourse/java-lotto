package validator;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class LottoValidator {
    public static void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호의 개수는 6개여야 합니다.");
        }
        if (hasDistinctNumber(numbers)) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
        if (numbers.stream().anyMatch(number -> !isValidNumber(number))) {
            throw new IllegalArgumentException("1~45 범위 이내여야 합니다.");
        }
        if (!isSorted(numbers)) {
            throw new IllegalArgumentException("로또 번호는 정렬되어야 합니다.");
        }
    }

    private static boolean isSorted(List<Integer> numbers) {
        List<Integer> sortedNumbers = numbers.stream().sorted().toList();
        return sortedNumbers.equals(numbers);
    }

    private static boolean isValidNumber(int number) {
        return number > 0 && number <= 45;
    }

    private static boolean hasDistinctNumber(List<Integer> numbers) {
        Set<Integer> noneDistinctNumbers = new HashSet<>(numbers);
        return noneDistinctNumbers.size() != numbers.size();
    }
}
