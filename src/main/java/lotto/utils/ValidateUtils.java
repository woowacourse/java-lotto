package lotto.utils;

import lotto.exception.LottoCustomException;

public class ValidateUtils {
    public static final String INPUT_MONEY_TYPE_ERROR_MESSAGE = "구입 금액은 숫자만 가능합니다.";

    public static int parseInt(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new LottoCustomException(INPUT_MONEY_TYPE_ERROR_MESSAGE);
        }
    }
}
