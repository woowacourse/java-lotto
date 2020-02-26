package lotto.util;

import lotto.domain.LottoNo;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoUtils {
    private static final String ERROR_MESSAGE_NOT_INTEGER = "숫자가 아닌 문자를 입력하였습니다.";

    public static Set<LottoNo> toLottoNoSet(String[] winLotto) {
        try {
            return Arrays.stream(winLotto)
                    .map(Integer::parseInt)
                    .map(LottoNo::new)
                    .collect(Collectors.toSet());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_MESSAGE_NOT_INTEGER);
        }
    }
}
