package lotto.domain.lottofactory.shufflerule;

import lotto.domain.lottofactory.LottoNumber;

import java.util.Collections;
import java.util.List;

public class RandomShuffle implements Shuffle {
    @Override
    public void shuffleLottoNumbers(List<LottoNumber> lottoNumbers) {
        Collections.shuffle(lottoNumbers);
    }
}
