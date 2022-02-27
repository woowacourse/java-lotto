package lotto.util;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class IntsToLottoConverter {

    private IntsToLottoConverter() {
    }

    public static Lotto convert(List<Integer> numbers) {
        return new Lotto(numbers.stream()
                .map(LottoNumber::new)
                .collect(toList()));
    }
}
