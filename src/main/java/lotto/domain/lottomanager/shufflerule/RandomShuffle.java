package lotto.domain.lottomanager.shufflerule;

import lotto.domain.lottomanager.LottoNumber;

import java.util.Collections;
import java.util.List;

public class RandomShuffle implements Shuffle {
    @Override
    public void shuffleLottoNumbers(List<LottoNumber> lottoNumbers) {
        Collections.shuffle(lottoNumbers);
    }
}
