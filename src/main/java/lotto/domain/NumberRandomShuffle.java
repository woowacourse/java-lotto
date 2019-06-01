package lotto.domain;

import java.util.Collections;
import java.util.List;

public class NumberRandomShuffle implements NumberShuffle {
    private static final int LOTTO_FROM_INDEX = 0;
    private static final int LOTTO_TO_INDEX = 6;

    @Override
    public List<LottoNumber> shuffle(List<LottoNumber> lottoNumbers) {
        Collections.shuffle(lottoNumbers);
        return lottoNumbers.subList(LOTTO_FROM_INDEX, LOTTO_TO_INDEX);
    }
}
