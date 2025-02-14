package model;

import java.util.HashSet;
import java.util.List;

public class WinningLotto {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int NUMBER_SIZE = 6;

    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        validateWinningNumbers(winningNumbers);
        validateBonusNumber(winningNumbers, bonusNumber);

        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateWinningNumbers(List<Integer> numbers) {
        numbers.forEach(this::validateRange);
        validateSize(numbers);
        validateDuplication(numbers);
    }

    private void validateBonusNumber(List<Integer> numbers, int bonusNumber) {
        validateRange(bonusNumber);
        validateOverlap(numbers, bonusNumber);
    }

    private void validateRange(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 번호는 1부터 45 사이여야 합니다.");
        }
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != NUMBER_SIZE) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplication(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복되어서는 안됩니다.");
        }
    }

    private void validateOverlap(List<Integer> numbers, int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호와 보너스 번호는 중복되어서는 안됩니다.");
        }
    }

    public int countOverlappedNumbers(List<Integer> numbers) {
        return (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public boolean isOverlappedBonusNumber(List<Integer> numbers) {
        return numbers.contains(bonusNumber);
    }
}
