package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateNumbers(numbers);
        this.numbers = numbers;
    }

    public int countMatches(List<Integer> winningNumbers) {
        Set<Integer> numberSet = new HashSet<>(this.numbers);
        Set<Integer> winningNumberSet = new HashSet<>(winningNumbers);
        numberSet.retainAll(winningNumberSet);
        return numberSet.size();
    }

    public boolean isBonusMatched(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    private void validateNumbers(List<Integer> numbers) {
        validateDuplicateNumbers(numbers);
        validateNumbersRange(numbers);
        validateNumbersCount(numbers);
    }

    private void validateNumbersCount(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또 숫자는 6개여야 합니다.");
        }
    }

    private void validateDuplicateNumbers(List<Integer> numbers) {
        Set<Integer> numSet = new HashSet<>(numbers);
        if (numSet.size() != numbers.size()) {
            throw new IllegalArgumentException("중복된 숫자가 있습니다.");
        }
    }

    private void validateNumbersRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException("숫자 범위가 벗어났습니다.");
            }
        }
    }
}
