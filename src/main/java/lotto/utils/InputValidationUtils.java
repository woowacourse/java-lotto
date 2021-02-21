package lotto.utils;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.exception.LessThanLottoPriceException;

public class InputValidationUtils {

    public static void validatePurchaseAmount(int value) {
        if (value < Lotto.LOTTO_PRICE) {
            throw new LessThanLottoPriceException();
        }
    }

    public static void validateWinningBonus(int value) {
        if (value <= LottoNumber.MIN || value > LottoNumber.MAX) {
            throw new IllegalArgumentException("보너스 번호는 1부터 45사이의 값이어야 합니다.");
        }
    }
}
