package lotto.domain.ShuffleRule;

import lotto.domain.LottoNumber;

import java.util.List;

public interface Shuffle {
    List<LottoNumber> getShuffledLottoNumbers(List<LottoNumber> lottoNumbers);
}
