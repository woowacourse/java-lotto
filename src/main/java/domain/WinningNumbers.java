package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningNumbers {

    private static final String NULL_WINNING_NUMBER_ERROR = "[ERROR] 로또 당첨번호에 null 값이 올 수 없습니다.";
    private static final String NULL_BONUS_NUMBER_ERROR = "[ERROR] 로또 보너스볼에 null 값이 올 수 없습니다.";
    private static final String DUPLICATED_NUMBER_ERROR = "[ERROR] 당첨번호와 보너스볼은 중복될 수 없습니다.";

    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningNumbers(Lotto winningNumbers, LottoNumber bonusNumber) {
        checkValidate(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public boolean isContain(LottoNumber lottoNumber) {
        return winningNumbers.isContain(lottoNumber);
    }

    public boolean isEqualToBonusNumber(LottoNumber lottoNumber) {
        return bonusNumber.equals(lottoNumber);
    }

    private void checkValidate(Lotto winningNumbers, LottoNumber bonusNumber) {
        checkNull(winningNumbers, bonusNumber);
        checkDuplicate(winningNumbers, bonusNumber);
    }

    private void checkNull(Lotto winningNumbers, LottoNumber bonusNumber) {
        checkWinningNumbersNull(winningNumbers);
        checkBonusNumberNull(bonusNumber);
    }

    private void checkWinningNumbersNull(Lotto winningNumbers) {
        if (winningNumbers == null) {
            throw new IllegalArgumentException(NULL_WINNING_NUMBER_ERROR);
        }
    }

    private void checkBonusNumberNull(LottoNumber bonusNumber) {
        if (bonusNumber == null) {
            throw new IllegalArgumentException(NULL_BONUS_NUMBER_ERROR);
        }
    }

    private void checkDuplicate(Lotto winningNumbers, LottoNumber bonusNumber) {
        if (winningNumbers.isContain(bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATED_NUMBER_ERROR);
        }

    }


}
