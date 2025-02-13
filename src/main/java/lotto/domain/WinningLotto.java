package lotto.domain;

import static lotto.util.Constant.LOTTO_NUMBER_MAX_RANGE;
import static lotto.util.Constant.LOTTO_NUMBER_MIN_RANGE;
import static lotto.util.ErrorHandler.INVALID_BONUS_DISTINCT;
import static lotto.util.ErrorHandler.INVALID_BONUS_NUMBER;
import static lotto.util.ErrorHandler.INVALID_RANGE;

import java.util.List;

public class WinningLotto {

    private final Lotto winningNumbers;
    private final int bonusBall;

    public WinningLotto(Lotto winningNumbers, String bonusBallInput) {
        this.winningNumbers = winningNumbers;
        int bonusBallNumber = parse(bonusBallInput);
        validateBonusBall(bonusBallNumber);
        this.bonusBall = bonusBallNumber;
    }

    private int parse(String bonusBallInput) {
        try {
            return Integer.parseInt(bonusBallInput);
        } catch (NumberFormatException e) {
            throw INVALID_BONUS_NUMBER.getException();
        }
    }

    private void validateBonusBall(int bonusBallNumber) {
        validateRange(bonusBallNumber);
        validateDistinct(bonusBallNumber);
    }

    private void validateRange(int bonusBallNumber) {
        if (bonusBallNumber < LOTTO_NUMBER_MIN_RANGE || bonusBallNumber > LOTTO_NUMBER_MAX_RANGE) {
            throw INVALID_RANGE.getException();
        }
    }

    private void validateDistinct(int bonusBallNumber) {
        List<Integer> winningLottoNumbers = winningNumbers.getLotto();
        if (winningLottoNumbers.contains(bonusBallNumber)) {
            throw INVALID_BONUS_DISTINCT.getException();
        }
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers.getLotto();
    }

    public int getBonusBall() {
        return bonusBall;
    }
}
