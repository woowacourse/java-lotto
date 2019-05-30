package lotto.domain.shufflerule;

import lotto.domain.lottofactory.LottoConstant;
import lotto.domain.lottofactory.LottoNumber;

import java.util.List;

public class FixShuffle implements Shuffle {
    @Override
    public List<LottoNumber> getShuffledLottoNumbers(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.subList(LottoConstant.SUBLIST_FIRST_INDEX, LottoConstant.LOTTO_NUMBER_SIZE);
    }
}
