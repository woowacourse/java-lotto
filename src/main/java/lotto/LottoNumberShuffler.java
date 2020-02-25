package lotto;

import java.util.List;

import lotto.domain.LottoNumber;
import lotto.domain.Lottos;

public interface LottoNumberShuffler {
    List<LottoNumber> shuffle(List<LottoNumber> lottoNumbers);
}
