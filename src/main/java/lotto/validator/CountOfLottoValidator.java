package lotto.validator;

import lotto.domain.Lotto;
import lotto.exception.CountOfManualLottoException;

public class CountOfLottoValidator {

    public static void validate(int count, long money) {
        checkPositive(count);
        checkLimit(count, money);
    }

    private static void checkPositive(int count) {
        if (count < 0) {
            throw new CountOfManualLottoException(CountOfManualLottoException.COUNT_OF_MANUAL_LOTTO_ONLY_POSITIVE_ERROR_MESSAGE);
        }
    }

    private static void checkLimit(int count, long money) {
        if (money / Lotto.LOTTO_PRICE < count) {
            throw new CountOfManualLottoException(CountOfManualLottoException.COUNT_OF_MANUAL_LOTTO_LIMIT_ERROR_MESSAGE);
        }
    }
}
