package exception.lotto;

import domain.lotto.LottoNumber;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumDuplicatedException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "로또 번호는 중복될 수 없습니다. : %s";

    public LottoNumDuplicatedException(final List<LottoNumber> value) {
        super(String.format(ERROR_MESSAGE, value.stream().map(LottoNumber::get).collect(Collectors.toList())));
    }
}
