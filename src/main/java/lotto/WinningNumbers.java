package lotto;

import java.util.List;

public class WinningNumbers {

    private static final String ERROR_DUPLICATION_WINNING_NUMBERS = "당첨 번호가 서로 중복되었습니다.";

    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public WinningNumbers(List<Integer> winningNumbers, int bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
        validateIsDuplicatedNumbers();
    }

    private void validateIsDuplicatedNumbers() {
        if (isDuplicate()) {
            throw new RuntimeException(ERROR_DUPLICATION_WINNING_NUMBERS);
        }
    }

    private boolean isDuplicate() {
        return winningNumbers.stream().distinct().count() != winningNumbers.size();
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
