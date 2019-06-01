package lotto.domain.generator;

import lotto.domain.LottoContainer;
import lotto.domain.LottoNumber;
import lotto.domain.NumberRandomShuffle;
import lotto.domain.generator.LottoNumberGenerator;

import java.util.List;

public class LottoRandomGenerator implements LottoNumberGenerator {
    private static final NumberRandomShuffle numberRandomShuffle = new NumberRandomShuffle();

    @Override
    public List<LottoNumber> generate() {
        List<LottoNumber> lottoNumbers = LottoContainer.createLottoNumbers();
        List<LottoNumber> shuffledNumbers = numberRandomShuffle.shuffle(lottoNumbers);
        return shuffledNumbers.subList(LOTTO_FROM_INDEX, LOTTO_TO_INDEX);
    }
}
