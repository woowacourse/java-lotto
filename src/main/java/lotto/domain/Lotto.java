package lotto.domain;

import static lotto.util.Constant.LOTTO_NUMBER_DELIMITER;
import static lotto.util.Constant.LOTTO_NUMBER_MAX_RANGE;
import static lotto.util.Constant.LOTTO_NUMBER_MIN_RANGE;
import static lotto.util.Constant.LOTTO_NUMBER_SIZE;
import static lotto.util.ErrorHandler.INVALID_LOTTO_NUMBER;
import static lotto.util.ErrorHandler.INVALID_LOTTO_RANGE;
import static lotto.util.ErrorHandler.INVALID_LOTTO_SIZE;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    private Set<Integer> lotto = new HashSet<>();

    public Lotto(Set<Integer> numbers) {
        validate(numbers);
        this.lotto = numbers;
    }

    private Set<Integer> parse(String input) {
        Set<String> result = Arrays.stream(input.split(LOTTO_NUMBER_DELIMITER)).collect(Collectors.toSet());
        return result.stream()
                .map(s -> validateAndParse(s.strip()))
                .collect(Collectors.toSet());
    }

    private int validateAndParse(String lottoNumberInput) {
        try {
            return Integer.parseInt(lottoNumberInput);
        } catch (NumberFormatException e) {
            throw INVALID_LOTTO_NUMBER.getException();
        }
    }

    private void validate(Set<Integer> numbers) {
        validateSize(numbers);
        validateRange(numbers);
    }

    private void validateSize(Set<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_SIZE) {
            throw INVALID_LOTTO_SIZE.getException();
        }
    }

    private void validateRange(Set<Integer> numbers) {
        for (Integer number : numbers) {
            validateNumberRange(number);
        }
    }

    private void validateNumberRange(int number) {
        if (number < LOTTO_NUMBER_MIN_RANGE || number > LOTTO_NUMBER_MAX_RANGE) {
            throw INVALID_LOTTO_RANGE.getException();
        }
    }

    public Set<Integer> getLottoNumbers() {
        return lotto;
    }
}
