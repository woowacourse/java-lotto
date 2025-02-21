package lotto.domain;

import static lotto.common.constant.BusinessRule.*;
import static lotto.common.constant.ErrorMessage.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lotto.common.utill.RandomWrapper;

public class Lotto {
    protected final List<Integer> numbers;

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
        if (numbers.stream().anyMatch(this::isNumberInRage)) {
            throw new IllegalArgumentException(ERROR_LOTTO_NUMBER_RANGE.getMessage());
        }
    }

    protected boolean isNumberInRage(int number) {
        return number < LOTTO_MINIMUM || number > LOTTO_MAXIMUM;
    }
    
    public MatchCount matchCount(WinningLotto winningLotto) {
        int count = (int)winningLotto.getLottoNumbers().stream()
            .filter(numbers::contains)
            .count();

        boolean isBonus = isContainsBonus(winningLotto.getBonus());

        return new MatchCount(count, isBonus);
    }

    public boolean isContainsBonus(int bonus) {
        return numbers.contains(bonus);
    }

    public List<Integer> getLottoNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
