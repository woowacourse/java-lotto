package lotto;

import java.util.List;

public class WinningNumbers {
    private final List<Integer> winningNumbers;

    public WinningNumbers(final List<Integer> winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public void validateBonusNumberDuplicated(final int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("당첨 번호와 보너스 번호는 중복될 수 없습니다.");
        }
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers.stream()
                .toList();
    }
}
