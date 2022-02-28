package lotto.model;

import java.util.List;

import lotto.util.InputValidator;

public class WinningLotto {

    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        InputValidator.validateContain(winningNumbers, bonusNumber);
        this.winningNumbers = InputValidator.validateLotto(winningNumbers);
        this.bonusNumber = InputValidator.validateBonusNumber(bonusNumber);
    }

    public Rank calculate(List<Integer> numbers) {
        return Rank.of(countMatchingNumber(numbers), isBonusNumber(numbers));
    }

    private int countMatchingNumber(List<Integer> numbers) {
        return (int)numbers.stream()
            .filter(winningNumbers::contains)
            .count();
    }

    private boolean isBonusNumber(List<Integer> numbers) {
        return numbers.contains(bonusNumber);
    }
}
