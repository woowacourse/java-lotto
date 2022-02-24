package lotto.receiver;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.exception.BonusNumberException;

public class BonusNumberReceiver {

    public static LottoNumber receive(String input, Lotto winningNumbers) {
        LottoNumber lottoNumber = LottoNumber.getByString(input);
        checkDuplication(winningNumbers, lottoNumber);
        return lottoNumber;
    }

    private static void checkDuplication(Lotto winningNumbers, LottoNumber lottoNumber) {
        if (winningNumbers.getLotto().contains(lottoNumber)) {
            throw new BonusNumberException(BonusNumberException.BONUS_NUMBER_DUPLICATION_ERROR_MESSAGE);
        }
    }
}
