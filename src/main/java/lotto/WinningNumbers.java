package lotto;

import java.util.List;

public class WinningNumbers {
    private static final String ERROR_SIZE = "[ERROR] 당첨 번호는 6개여야 합니다";
    private static final String ERROR_DUPLICATE = "[ERROR] 당첨 번호는 중복되면 안됩니다";
    private static final int WINNING_NUMBERS_SIZE = 6;

    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public WinningNumbers(List<Integer> winningNumbers, int bonusNumber) {
        validate(winningNumbers);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validate(List<Integer> winningNumbers) {
        checkSize(winningNumbers);
        checkDuplicate(winningNumbers);
    }

    private void checkSize(List<Integer> winningNumbers) {
        if (winningNumbers.size() != WINNING_NUMBERS_SIZE) {
            throw new IllegalArgumentException(ERROR_SIZE);
        }
    }

    private void checkDuplicate(List<Integer> winningNumbers) {
        if (getDistinctCount(winningNumbers) != winningNumbers.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATE);
        }
    }

    private int getDistinctCount(List<Integer> winningNumbers) {
        return (int) winningNumbers.stream()
                .distinct()
                .count();
    }

    public boolean match(int number) {
        return winningNumbers.contains(number);
    }

    public boolean matchBonusNumber(Lotto lotto) {
        return lotto.contains(this.bonusNumber);
    }
}
