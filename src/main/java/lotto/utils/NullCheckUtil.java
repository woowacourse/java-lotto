package lotto.utils;

import lotto.domain.LottoNumber;

import java.util.List;

public class NullCheckUtil {
    private static final String ERROR_NULL = "null error";

    public static void checkNull(String input) {
        if (input == null) {
            throw new NullPointerException(ERROR_NULL);
        }
    }

    public static void checkNull(Integer number) {
        if (number == null) {
            throw new NullPointerException(ERROR_NULL);
        }
    }

    private static void checkNull(LottoNumber number) {
        if (number == null) {
            throw new NullPointerException(ERROR_NULL);
        }
    }

    public static void checkNull(List<LottoNumber> lottoNumbers) {
        for (LottoNumber lottoNumber : lottoNumbers) {
            checkNull(lottoNumber);
        }
    }

}
