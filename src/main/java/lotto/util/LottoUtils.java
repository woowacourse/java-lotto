package lotto.util;

import lotto.domain.LottoNo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoUtils {
    public static final String ERROR_MESSAGE_NOT_INTEGER = "숫자가 아닌 문자를 입력하였습니다.";

    public static List<LottoNo> toLottoNoList(String[] winLotto) {
        try {
            return Arrays.stream(winLotto)
                    .map(Integer::parseInt)
                    .map(LottoNo::new)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_MESSAGE_NOT_INTEGER);
        }
    }

}
