package model;

import dto.LottoDto;
import error.ErrorMessage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    public static final int PRICE = 1_000;
    private List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateNumbers(numbers);
        this.numbers = numbers;
    }

    public int countMatches(Lotto lotto) {
        Set<Integer> numberSet = new HashSet<>(this.numbers);
        Set<Integer> winningNumberSet = new HashSet<>(lotto.numbers);
        numberSet.retainAll(winningNumberSet);
        return numberSet.size();
    }

    public boolean isBonusMatched(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    public LottoDto toDto() {
        return new LottoDto(new ArrayList<>(numbers));
    }

    private void validateNumbers(List<Integer> numbers) {
        validateDuplicateNumbers(numbers);
        validateNumbersRange(numbers);
        validateNumbersCount(numbers);
    }

    private void validateNumbersCount(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_COUNT.getMessage());
        }
    }

    private void validateDuplicateNumbers(List<Integer> numbers) {
        Set<Integer> numSet = new HashSet<>(numbers);
        if (numSet.size() != numbers.size()) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_NUMBERS_FOUND.getMessage());
        }
    }

    private void validateNumbersRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            validateNumberRange(number);
        }
    }

    private void validateNumberRange(Integer number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_OUT_OF_RANGE.getMessage());
        }
    }
}
