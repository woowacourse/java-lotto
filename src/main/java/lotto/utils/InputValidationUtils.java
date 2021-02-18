package lotto.utils;

import lotto.exception.LessThanLottoPriceException;

public class InputValidationUtils {

    public static void validatePurchaseAmount(int value) {
        if (value < 1000) {
            throw new LessThanLottoPriceException();
        }
    }

    public static void validateWinningBonus(int value) {
        if (value <= 0 || value > 45) {
            throw new IllegalArgumentException("보너스 번호는 1부터 45사이의 값이어야 합니다.");
        }
    }
}
