package lotto.utils;

import lotto.exception.LottoCustomException;

public class ValidateUtils {

    public static int parseInt(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (Exception e) {
            throw new LottoCustomException("구입 금액은 숫자만 가능합니다.");
        }
    }
}
