package domain;

import java.util.List;

public class WinningLotto {

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        validateDuplicatedNumber(winningNumbers, bonusNumber);
    }

    private void validateDuplicatedNumber(List<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException();
        }
    }
}
