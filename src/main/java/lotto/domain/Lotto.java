package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

    private static final String ERROR_NUMBER_SIX_MESSAGE = "로또 숫자는 6개여야 합니다.";
    private static final String ERROR_NUMBERS_RANGE_MESSAGE = "로또 숫자 범위는 1 ~ 45입니다.";
    private static final String ERROR_DUPLICATION_MESSAGE = "로또 숫자는 중복되면 안됩니다.";

    private static final int LOTTO_SIZE = 6;
    private static final int LOTTO_MAXIMUM = 45;
    private static final int LOTTO_MINIMUM = 1;

    private List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateNumberSix(numbers);
        validateDuplication(numbers);
        validateRange(numbers);

        this.numbers = new ArrayList<>(numbers);
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    private void validateNumberSix(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_NUMBER_SIX_MESSAGE);
        }
    }

    private void validateDuplication(List<Integer> numbers) {
        Set<Integer> set = new HashSet<>(numbers);
        if (set.size() != numbers.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATION_MESSAGE);
        }
    }

    private void validateRange(List<Integer> numbers) {
        int max = numbers.stream().max(Comparator.comparingInt(o -> o)).get();
        int min = numbers.stream().min(Comparator.comparingInt(o -> o)).get();
        if (min < LOTTO_MINIMUM || LOTTO_MAXIMUM < max) {
            throw new IllegalArgumentException(ERROR_NUMBERS_RANGE_MESSAGE);
        }
    }
}
