package lotto.domain.util.impl;

import java.util.Collections;
import java.util.List;
import lotto.domain.LottoNumber;
import lotto.domain.util.LottoGenerator;

public class RandomLottoGenerator implements LottoGenerator {
    @Override
    public List<LottoNumber> generate(int size) {
        List<LottoNumber> lottoNumbers = LottoNumber.values();
        Collections.shuffle(lottoNumbers);
        return lottoNumbers.subList(0, size);
    }
}
