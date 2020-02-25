package lotto.domain;

import java.util.Collections;
import java.util.List;

public class CollectionLottoNumberShuffler implements LottoNumberShuffler {

    @Override
    public List<LottoNumber> shuffle(List<LottoNumber> lottoNumbers) {
        Collections.shuffle(lottoNumbers);
        return lottoNumbers;
    }
}
