package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.domain.domainconstants.DomainConstants.MINIMUM_LOTTO_NUMBER;
import static lotto.domain.domainconstants.DomainConstants.MAXIMUM_LOTTO_NUMBER;

class AutoLottoGenerator {
    private final List<LottoNumber> lottoNumbers;

    AutoLottoGenerator() {
        lottoNumbers = IntStream.rangeClosed(MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }

    Lotto makeLotto() {
        Collections.shuffle(lottoNumbers);
        return new Lotto(lottoNumbers.stream()
                .limit(6)
                .collect(Collectors.toList()));
    }
}
