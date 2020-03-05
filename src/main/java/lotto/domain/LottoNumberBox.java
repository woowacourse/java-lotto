package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class LottoNumberBox {
    static List<LottoNumber> create() {
        return IntStream.rangeClosed(LottoNumber.MIN, LottoNumber.MAX)
            .mapToObj(LottoNumber::create)
            .collect(Collectors.toList());
    }
}
