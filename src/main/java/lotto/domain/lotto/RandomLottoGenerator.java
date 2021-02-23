package lotto.domain.lotto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.number.LottoNumber;

public class RandomLottoGenerator {

    private static final RandomLottoGenerator RANDOM_LOTTO_GENERATOR = new RandomLottoGenerator();

    private final List<LottoNumber> lottoNumbers;

    private RandomLottoGenerator() {
        lottoNumbers = LottoNumber.getAllLottoNumbers();
    }

    public static RandomLottoGenerator getInstance() {
        return RANDOM_LOTTO_GENERATOR;
    }

    public LottoNumbers nextLottoNumbers() {
        Collections.shuffle(lottoNumbers);

        return new LottoNumbers(lottoNumbers.stream()
            .limit(LottoNumbers.getLottoNumberCount())
            .collect(Collectors.toSet()));
    }
}
