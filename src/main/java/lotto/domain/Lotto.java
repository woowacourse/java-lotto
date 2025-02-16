package lotto.domain;

import static lotto.common.constant.Constant.*;
import static lotto.common.exception.ErrorMessage.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import java.util.Set;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);

        this.numbers = new ArrayList<>(numbers);
    }

    private static void validate(List<Integer> numbers) {
        validateNull(numbers);
        validateSize(numbers);
        validateDuplicate(numbers);
    }

    private static void validateNull(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException(ERROR_LOTTO_SIZE);
        }
    }

    private static void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ERROR_LOTTO_SIZE);
        }
    }

    private static void validateDuplicate(List<Integer> numbers) {
        Set<Integer> set = new HashSet<>();
        for (int number : numbers) {

            if (!set.add(number)) {
                throw new IllegalArgumentException(ERROR_LOTTO_NUMBER_DUPLICATE);
            }
        }
    }

    public MatchResult countMatchingNumbers(Lotto matchLotto, int bonus) {

        int count = (int) numbers.stream()
                .filter(matchLotto::contains)
                .count();

        return new MatchResult(count, contains(bonus));
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

}
