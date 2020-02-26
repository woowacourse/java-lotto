package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class LottoNumberBox {
    private static final int ONE = 1;

    static List<LottoNumber> create() {
        return IntStream.range(LottoNumber.MIN, LottoNumber.MAX + ONE)
            .mapToObj(LottoNumber::new)
            .collect(Collectors.toList());
    }

}
