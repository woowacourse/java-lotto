package lotto.domain.ShuffleRule;

import lotto.domain.LottoConstant;
import lotto.domain.LottoNumber;

import java.util.List;

public class FixShuffle implements Shuffle {
    @Override
    public List<LottoNumber> getShuffledLottoNumbers(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.subList(LottoConstant.SUBLIST_FIRST_INDEX, LottoConstant.LOTTO_NUMBER_SIZE);
    }
}
