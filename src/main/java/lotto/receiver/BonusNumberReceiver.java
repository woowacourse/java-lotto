package lotto.receiver;

import java.util.List;
import lotto.domain.LottoNumber;
import lotto.exception.BonusNumberException;

public class BonusNumberReceiver {

    public static LottoNumber receive(String input, List<LottoNumber> winningNumbers) {
        LottoNumber lottoNumber = LottoNumber.getLottoNumber(input);
        checkDuplication(lottoNumber, winningNumbers);
        return lottoNumber;
    }

    private static void checkDuplication(LottoNumber lottoNumber, List<LottoNumber> winningNumbers) {
        if (winningNumbers.contains(lottoNumber)) {
            throw new BonusNumberException(BonusNumberException.BONUS_NUMBER_DUPLICATION_ERROR_MESSAGE);
        }
    }
}
