package lotto.domain;

import static lotto.common.constant.Constant.*;
import static lotto.common.exception.ErrorMessage.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import java.util.Set;
import lotto.dto.MatchCountDto;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);

        this.numbers = new ArrayList<>(numbers);
        sortNumbers(this.numbers);
    }

    public MatchCountDto countMatchingNumbers(Lotto matchLotto, int bonus) {

        int count = (int) numbers.stream()
                .filter(matchLotto::containsNumber)
                .count();

        return new MatchCountDto(count, containsNumber(bonus));
    }

    public boolean containsNumber(int number) {
        return numbers.contains(number);
    }

    private static void validate(List<Integer> numbers) {
        validateNull(numbers);
        validateSize(numbers);
        validateRange(numbers);
        validateDuplicate(numbers);
    }

    private static void validateNull(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException(ERROR_LOTTO_SIZE);
        }
    }

    private static void validateSize(List<Integer> numbers) {
        if (isCorrectedSize(numbers)) {
            throw new IllegalArgumentException(ERROR_LOTTO_SIZE);
        }
    }

    private static boolean isCorrectedSize(List<Integer> numbers) {
        return numbers.size() != LOTTO_SIZE;
    }

    private static void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            checkLottoRange(number);
        }
    }

    private static void checkLottoRange(int number) {
        if (isNumberInRage(number)) {
            throw new IllegalArgumentException(ERROR_NUMBER_RANGE);
        }
    }

    private static void validateDuplicate(List<Integer> numbers) {
        Set<Integer> set = new HashSet<>();
        for(int number: numbers) {

            if(!set.add(number)) {
                throw new IllegalArgumentException(ERROR_LOTTO_NUMBER_DUPLICATE);
            }
        }
    }

    private static boolean isNumberInRage(int number) {
        return number < LOTTO_RANGE_MINIMUM || number > LOTTO_RANGE_MAXIMUM;
    }

    private static void sortNumbers(List<Integer> numbers) {
        Collections.sort(numbers);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
