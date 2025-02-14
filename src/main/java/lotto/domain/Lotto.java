package lotto.domain;

import static lotto.common.constant.Constant.*;
import static lotto.common.constant.ErrorMessage.*;

import java.util.Comparator;
import java.util.List;

import lotto.dto.MatchCountDto;

public record Lotto(List<Integer> numbers) {
    public Lotto {
        validate(numbers);
        sortNumbers(numbers);
    }

    private void validate(List<Integer> numbers) {
        validateNull(numbers);
        validateSize(numbers);
        validateRange(numbers);
    }

    private void validateNull(List<Integer> numbers) {
        if (numbers.isEmpty()) {
            throw new IllegalArgumentException(ERROR_LOTTO_SIZE.getMessage());
        }
    }

    private void validateSize(List<Integer> numbers) {
        if (isCorrectedSize(numbers)) {
            throw new IllegalArgumentException(ERROR_LOTTO_SIZE.getMessage());
        }
    }

    private static boolean isCorrectedSize(List<Integer> numbers) {
        return numbers.size() != LOTTO_SIZE;
    }

    private void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            checkLottoRange(number);
        }
    }

    private void checkLottoRange(int number) {
        if (isNumberInRage(number)) {
            throw new IllegalArgumentException(ERROR_LOTTO_NUMBER_RANGE.getMessage());
        }
    }

    private boolean isNumberInRage(int number) {
        return number < LOTTO_MINIMUM || number > LOTTO_MAXIMUM;
    }

    private void sortNumbers(List<Integer> numbers) {
        numbers.sort(Comparator.naturalOrder());
    }

    public MatchCountDto matchCount(Lotto matchLotto, int bonus) {
        int count = 0;
        for (int i : matchLotto.numbers()) {
            if (isContainsBonus(i)) {
                count++;
            }
        }
        return new MatchCountDto(count, isContainsBonus(bonus));
    }

    public boolean isContainsBonus(int bonus) {
        return numbers.contains(bonus);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
