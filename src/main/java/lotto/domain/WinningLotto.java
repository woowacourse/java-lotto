package lotto.domain;

import static lotto.util.Constant.LOTTO_NUMBER_MAX_RANGE;
import static lotto.util.Constant.LOTTO_NUMBER_MIN_RANGE;
import static lotto.util.ErrorHandler.INVALID_LOTTO_BONUS_DISTINCT;
import static lotto.util.ErrorHandler.INVALID_LOTTO_NUMBER;
import static lotto.util.ErrorHandler.INVALID_LOTTO_RANGE;

import java.util.Set;

public class WinningLotto {

    private final Lotto winningNumbers;
    private final int bonusBall;

    public WinningLotto(Lotto winningNumbers, String bonusBallInput) {
        int bonusBallNumber = validateAndParse(bonusBallInput);
        validateBonusBall(winningNumbers, bonusBallNumber);
        this.winningNumbers = winningNumbers;
        this.bonusBall = bonusBallNumber;
    }

    private int validateAndParse(String bonusBallInput) {
        try {
            return Integer.parseInt(bonusBallInput);
        } catch (NumberFormatException e) {
            throw INVALID_LOTTO_NUMBER.getException();
        }
    }

    private void validateBonusBall(Lotto winningNumbers, int bonusBallNumber) {
        validateRange(bonusBallNumber);
        validateDistinct(winningNumbers, bonusBallNumber);
    }

    private void validateRange(int bonusBallNumber) {
        if (bonusBallNumber < LOTTO_NUMBER_MIN_RANGE || bonusBallNumber > LOTTO_NUMBER_MAX_RANGE) {
            throw INVALID_LOTTO_RANGE.getException();
        }
    }

    private void validateDistinct(Lotto winningNumbers, int bonusBallNumber) {
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
