package lotto.receiver.validator;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.exception.BonusNumberException;

public class BonusNumberValidator {

    public static void validate(LottoNumber lottoNumber, Lotto winningNumbers) {
        checkDuplicate(lottoNumber, winningNumbers);
    }

    private static void checkDuplicate(LottoNumber lottoNumber, Lotto winningNumbers) {
        if (isDuplicate(lottoNumber, winningNumbers)) {
            throw new BonusNumberException(BonusNumberException.BONUS_NUMBER_DUPLICATION_ERROR_MESSAGE);
        }
    }

    private static boolean isDuplicate(LottoNumber lottoNumber, Lotto winningNumbers) {
        return winningNumbers.getLotto().contains(lottoNumber);
    }
}
