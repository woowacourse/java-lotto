package lotto.domain.lottogenerator;

import lotto.domain.LottoNumber;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MockLottoGeneratingStrategy implements LottoGeneratingStrategy {
    @Override
    public List<LottoNumber> generate() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                LottoNumber.getNumber(1),
                LottoNumber.getNumber(2),
                LottoNumber.getNumber(3),
                LottoNumber.getNumber(4),
                LottoNumber.getNumber(5),
                LottoNumber.getNumber(6));

        return Collections.unmodifiableList(lottoNumbers);
    }
}
