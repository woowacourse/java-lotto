package lotto.validator;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.exception.BonusNumberException;

public class BonusNumberValidator {

    public static void validate(LottoNumber lottoNumber, Lotto winningLotto) {
        checkDuplicate(lottoNumber, winningLotto);
    }

    private static void checkDuplicate(LottoNumber lottoNumber, Lotto winningLotto) {
        if (winningLotto.isContain(lottoNumber)) {
            throw new BonusNumberException(BonusNumberException.BONUS_NUMBER_DUPLICATION_ERROR_MESSAGE);
        }
    }
}
