package lotto.domain;

import java.util.List;

public interface LottoNumberShuffler {
    List<LottoNumber> shuffle(List<LottoNumber> lottoNumbers);
}
