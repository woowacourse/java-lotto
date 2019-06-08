package lotto.domain.generator;

import lotto.domain.LottoContainer;
import lotto.domain.LottoNumber;
import lotto.domain.NumberRandomShuffle;

import java.util.List;

class LottoRandomGenerator {
    private static final int LOTTO_FROM_INDEX = 0;
    private static final int LOTTO_TO_INDEX = 6;

    static List<LottoNumber> generate() {
        List<LottoNumber> lottoNumbers = LottoContainer.createLottoNumbers();
        List<LottoNumber> shuffledNumbers = NumberRandomShuffle.shuffle(lottoNumbers);
        return shuffledNumbers.subList(LOTTO_FROM_INDEX, LOTTO_TO_INDEX);
    }
}
