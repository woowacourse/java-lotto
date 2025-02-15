package lotto.domain;

import static lotto.common.constant.Constant.*;
import static lotto.common.constant.ErrorMessage.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lotto.common.utill.RandomWrapper;

public record Lotto(List<Integer> numbers) {
    public Lotto(List<Integer> numbers) {
        List<Integer> lottoNumbers = numbers.stream()
            .sorted()
            .collect(Collectors.toList());
        validate(lottoNumbers);
        this.numbers = Collections.unmodifiableList(lottoNumbers);
    }

    public static Lotto generateLotto() {
        List<Integer> numbers = RandomWrapper.getRandomIntList(LOTTO_MINIMUM, LOTTO_MAXIMUM, LOTTO_SIZE);
        return new Lotto(numbers);
    }

    private void validate(List<Integer> numbers) {
        validateNull(numbers);
        validateSize(numbers);
        validateRange(numbers);
    }

    private void validateNull(List<Integer> numbers) {
        if (numbers.isEmpty()) {
            throw new IllegalArgumentException(ERROR_INCORRECT_LOTTO_SIZE.getMessage());
        }
    }

    private void validateSize(List<Integer> numbers) {
        if (isCorrectedSize(numbers)) {
            throw new IllegalArgumentException(ERROR_INCORRECT_LOTTO_SIZE.getMessage());
        }
    }

    private static boolean isCorrectedSize(List<Integer> numbers) {
        return numbers.size() != LOTTO_SIZE;
    }

    private void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            validateLottoNumberRange(number);
        }
    }

    private void validateLottoNumberRange(int number) {
        if (isNumberInRage(number)) {
            throw new IllegalArgumentException(ERROR_LOTTO_NUMBER_RANGE.getMessage());
        }
    }

    private boolean isNumberInRage(int number) {
        return number < LOTTO_MINIMUM || number > LOTTO_MAXIMUM;
    }

    public MatchCount matchCount(Lotto matchLotto, int bonus) {
        int count = 0;
        for (int i : matchLotto.numbers()) {
            if (isContainsBonus(i)) {
                count++;
            }
        }
        return new MatchCount(count, isContainsBonus(bonus));
    }

    public void validateBonus(int bonus) {
        validateLottoNumberRange(bonus);
        validateDuplicatedBonus(bonus);
    }

    public boolean isContainsBonus(int bonus) {
        return numbers.contains(bonus);
    }

    private void validateDuplicatedBonus(int bonus) {
        if (isContainsBonus(bonus)) {
            throw new IllegalArgumentException(ERROR_DUPLICATED_BONUS_NUMBER.getMessage());
        }
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
