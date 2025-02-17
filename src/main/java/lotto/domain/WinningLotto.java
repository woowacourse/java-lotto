package lotto.domain;

import static lotto.util.Constant.LOTTO_NUMBER_MAX_RANGE;
import static lotto.util.Constant.LOTTO_NUMBER_MIN_RANGE;
import static lotto.util.ErrorHandler.INVALID_LOTTO_BONUS_DISTINCT;
import static lotto.util.ErrorHandler.INVALID_LOTTO_RANGE;

import java.util.Set;

public class WinningLotto {

    private final Lotto winningNumbers;
    private final int bonusBall;

    public WinningLotto(Lotto winningNumbers, int bonusBall) {
        validateBonusBall(winningNumbers, bonusBall);
        this.winningNumbers = winningNumbers;
        this.bonusBall = bonusBall;
    }

    private void validateBonusBall(Lotto winningNumbers, int bonusBallNumber) {
        validateRange(bonusBallNumber);
        validateDuplicate(winningNumbers, bonusBallNumber);
    }

    private void validateRange(int bonusBallNumber) {
        if (bonusBallNumber < LOTTO_NUMBER_MIN_RANGE || bonusBallNumber > LOTTO_NUMBER_MAX_RANGE) {
            throw INVALID_LOTTO_RANGE.getException();
        }
    }

    private void validateDuplicate(Lotto winningNumbers, int bonusBallNumber) {
        Set<Integer> winningLottoNumbers = winningNumbers.getLottoNumbers();
        if (winningLottoNumbers.contains(bonusBallNumber)) {
            throw INVALID_LOTTO_BONUS_DISTINCT.getException();
        }
    }

    public Set<Integer> getWinningNumbers() {
        return winningNumbers.getLottoNumbers();
    }

    public int getBonusBall() {
        return bonusBall;
    }
}
