package lotto.domain;

import static lotto.util.Constant.LOTTO_NUMBER_MAX_RANGE;
import static lotto.util.Constant.LOTTO_NUMBER_MIN_RANGE;
import static lotto.util.ExceptionHandler.INVALID_BONUS_NUMBER_DISTINCT;
import static lotto.util.ExceptionHandler.INVALID_LOTTO_NUMBER_RANGE;

import java.util.List;

public class WinningLotto {

    private final Lotto winningNumbers;
    private final int bonusBall;

    public WinningLotto(Lotto winningNumbers, int bonusBall) {
        this.winningNumbers = winningNumbers;
        validateBonusBall(bonusBall);
        this.bonusBall = bonusBall;
    }

    private void validateBonusBall(int bonusBallNumber) {
        validateRange(bonusBallNumber);
        validateDistinct(bonusBallNumber);
    }

    private void validateRange(int bonusBallNumber) {
        if (bonusBallNumber < LOTTO_NUMBER_MIN_RANGE || bonusBallNumber > LOTTO_NUMBER_MAX_RANGE) {
            throw INVALID_LOTTO_NUMBER_RANGE.getException();
        }
    }

    private void validateDistinct(int bonusBallNumber) {
        List<Integer> winningLottoNumbers = winningNumbers.getLotto();
        if (winningLottoNumbers.contains(bonusBallNumber)) {
            throw INVALID_BONUS_NUMBER_DISTINCT.getException();
        }
    }

    int matchWinningNumbers(Lotto lottoTicket) {
        return (int) lottoTicket
                .getLotto()
                .stream()
                .filter(winningNumbers.getLotto()::contains)
                .count();
    }

    boolean matchBonusBall(Lotto lottoTicket) {
        List<Integer> ticketNumbers = lottoTicket.getLotto();
        return ticketNumbers.contains(bonusBall);
    }
}
