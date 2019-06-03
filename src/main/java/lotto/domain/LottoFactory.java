package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class LottoFactory {

    public Lotto create(final List<Integer> lottoNumbers) {
        return Lotto.of(lottoNumbers.stream()
                .map(number -> LottoNumber.of(number))
                .collect(Collectors.toList()));
    }
}
