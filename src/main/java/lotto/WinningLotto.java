package lotto;

import java.util.List;

public class WinningLotto {
    private final WinningNumbers winningNumbers;
    private final int bonusNumber;

    public WinningLotto(final WinningNumbers winningNumbers, final int bonusNumber) {
        validateBonusNumberDuplicated(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers.getWinningNumbers();
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    private void validateBonusNumberDuplicated(final WinningNumbers winningNumbers, final int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("당첨 번호와 보너스 번호는 중복될 수 없습니다.");
        }
    }
}
