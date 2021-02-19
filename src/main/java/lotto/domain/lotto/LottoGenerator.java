package lotto.domain.lotto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.number.LottoNumber;

public class LottoGenerator {

    private static final LottoGenerator LOTTO_GENERATOR = new LottoGenerator();

    private final List<LottoNumber> lottoNumbers;

    private LottoGenerator() {
        lottoNumbers = LottoNumber.getAllLottoNumbers();
    }

    public static LottoGenerator getInstance() {
        return LOTTO_GENERATOR;
    }

    public LottoNumbers nextLottoNumbers() {
        Collections.shuffle(lottoNumbers);

        return new LottoNumbers(lottoNumbers.stream()
            .limit(LottoNumbers.getLottoNumberCount())
            .collect(Collectors.toSet()));
    }
}
