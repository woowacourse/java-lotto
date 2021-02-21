package lotto.utils;

import lotto.exception.LottoCustomException;

public class RefineUtils {

    public static final String INPUT_MONEY_TYPE_ERROR_MESSAGE = "자연수를 입력해주세요.";

    public static int refineIntegerValue(final String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new LottoCustomException(INPUT_MONEY_TYPE_ERROR_MESSAGE);
        }
    }
}
