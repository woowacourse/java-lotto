package lotto.exception.Lotto;

import java.util.List;
import java.util.stream.Collectors;

public class LottoNumberCountException extends LottoException {
    private static final String ERROR_MESSAGE =
            "[ERROR] 당첨 번호는 중복되지 않는 6개의 숫자여야 합니다. 입력된 값 : %s";

    public LottoNumberCountException(List<Integer> lottoNumbers) {
        super(String.format(ERROR_MESSAGE,
                lottoNumbers.stream().map(String::valueOf).collect(Collectors.joining(","))));
    }
}