package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static lotto.constant.ErrorMessage.LOTTO_NUMBER_RANGE;
import static lotto.constant.Limit.MAX_LOTTO_NUMBER;
import static lotto.constant.Limit.MIN_LOTTO_NUMBER;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(final Set<Integer> numbers) {
        for (final int number : numbers) {
            validateLottoNumber(number);
        }
        this.numbers = getSortedNumbers(numbers);
    }

    private List<Integer> getSortedNumbers(final Set<Integer> numbers) {
        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers);
        return sortedNumbers;
    }

    public static void validateLottoNumber(final int lottoNumber) {
        if (lottoNumber < MIN_LOTTO_NUMBER.getValue() || lottoNumber > MAX_LOTTO_NUMBER.getValue()) {
            throw new IllegalArgumentException(LOTTO_NUMBER_RANGE.getErrorMessage());
        }
    }

    public boolean hasNumber(int number) {
        return numbers.contains(number);
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}
