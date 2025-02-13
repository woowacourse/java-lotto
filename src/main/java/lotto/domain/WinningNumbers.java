package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningNumbers {

    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public WinningNumbers(List<Integer> winningNumbers, int bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
        validateWinningNumberRange();
        validateBonusNumberRange();
        validateWinningNumberSize();
        validateDuplicateWinningNumber();
        validateExistBonusNumber();
    }

    private void validateWinningNumberRange() {
        for (Integer winningNumber : winningNumbers) {
            if (winningNumber < 1 || winningNumber > 45) {
                throw new IllegalArgumentException("[ERROR] 범위를 벗어나는 숫자입니다.");
            }
        }
    }

    private void validateBonusNumberRange() {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("[ERROR] 범위를 벗어나는 숫자입니다.");
        }
    }

    private void validateDuplicateWinningNumber() {
        Set<Integer> set = new HashSet<>(winningNumbers);
        if (set.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복될 수 없습니다.");
        }
    }

    private void validateWinningNumberSize() {
        if (winningNumbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 올바른 당첨 번호 갯수를 입력해 주세요.");
        }
    }

    private void validateExistBonusNumber() {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }


}
