package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoGenerator implements LottoGenerator {

    private static final int LOTTO_NUMBER_START_INDEX = 0;
    private static final int LOTTO_NUMBER_END_INDEX = 6;

    public List<LottoNumber> makeLottos() {
        List<LottoNumber> lottoNumbers = new ArrayList<>(LottoNumber.values());
        Collections.shuffle(lottoNumbers);
        return lottoNumbers.subList(LOTTO_NUMBER_START_INDEX, LOTTO_NUMBER_END_INDEX);
    }
}
