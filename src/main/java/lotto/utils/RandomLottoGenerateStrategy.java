package lotto.utils;

import static lotto.domain.Lotto.LOTTO_SIZE;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

public class RandomLottoGenerateStrategy implements LottoGenerateStrategy {

    @Override
    public Lotto generate() {
        List<LottoNumber> originLottoNumbers = LottoNumbersGenerator.getOriginLottoNumbers();
        Collections.shuffle(originLottoNumbers);
        return new Lotto(originLottoNumbers.subList(0, LOTTO_SIZE).stream()
                .sorted()
                .collect(Collectors.toList()));
    }
}
