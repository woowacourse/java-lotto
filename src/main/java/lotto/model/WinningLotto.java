package lotto.model;

import java.util.List;

public class WinningLotto {

    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        validateWinningNumbers(winningNumbers);
        validateBonusNumber(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public void checkRank(Lottos lottos) {
        lottos.calculateRanks(winningNumbers, bonusNumber);
    }

    private void validateWinningNumbers(List<Integer> numbers) {
        if (!isValidLength(numbers) || !isValidRange(numbers) || isDuplicate(numbers)) {
            throw new RuntimeException();
        }
    }

    private boolean isValidLength(List<Integer> numbers) {
        return numbers.size() == 6;
    }

    private boolean isValidRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < 1 || number > 45) {
                return false;
            }
        }
        return true;
    }

    private boolean isDuplicate(List<Integer> numbers) {
        return numbers.size() != numbers.stream().distinct().count();
    }

    public void validateBonusNumber(List<Integer> winningNumbers, int bonusNumber) throws RuntimeException {
        if (winningNumbers.contains(bonusNumber)) {
            throw new RuntimeException();
        }
    }
}
