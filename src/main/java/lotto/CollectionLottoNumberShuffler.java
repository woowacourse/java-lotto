package lotto;

import java.util.Collections;
import java.util.List;

import lotto.domain.LottoNumber;

public class CollectionLottoNumberShuffler implements LottoNumberShuffler {

    @Override
    public List<LottoNumber> shuffle(List<LottoNumber> lottoNumbers) {
        Collections.shuffle(lottoNumbers);
        return lottoNumbers;
    }
}
