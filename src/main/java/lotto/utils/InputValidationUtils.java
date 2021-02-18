package lotto.utils;

import lotto.exception.LessThanLottoPriceException;

public class InputValidationUtils {

    public static void validatePurchaseAmount(int value) {
        if (value < 1000) {
            throw new LessThanLottoPriceException();
        }
    }
}
