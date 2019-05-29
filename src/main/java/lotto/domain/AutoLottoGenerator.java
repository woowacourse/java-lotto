package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class AutoLottoGenerator {
    private final List<LottoNumber> lottoNumbers;

    AutoLottoGenerator() {
        lottoNumbers = IntStream.rangeClosed(1, 45)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }

    Lotto makeAutoLotto() {
        Collections.shuffle(lottoNumbers);
        return new Lotto(lottoNumbers.stream()
                .limit(6)
                .collect(Collectors.toList()));
    }
}
