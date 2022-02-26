package lotto.validator;

import lotto.domain.LottoNumber;
import lotto.domain.WinningLotto;
import lotto.exception.BonusNumberException;

public class BonusNumberValidator {

    public static void validate(LottoNumber lottoNumber, WinningLotto winningNumbers) {
        checkDuplicate(lottoNumber, winningNumbers);
    }

    private static void checkDuplicate(LottoNumber lottoNumber, WinningLotto winningNumbers) {
        if (winningNumbers.isContain(lottoNumber)) {
            throw new BonusNumberException(BonusNumberException.BONUS_NUMBER_DUPLICATION_ERROR_MESSAGE);
        }
    }
}
