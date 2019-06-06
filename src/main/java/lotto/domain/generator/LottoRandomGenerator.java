package lotto.domain.generator;

import lotto.domain.LottoContainer;
import lotto.domain.LottoNumber;
import lotto.domain.NumberRandomShuffle;
import lotto.domain.generator.LottoNumberGenerator;

import java.util.List;

public class LottoRandomGenerator implements LottoNumberGenerator {
    @Override
    public List<LottoNumber> generate() {
        List<LottoNumber> lottoNumbers = LottoContainer.createLottoNumbers();
        List<LottoNumber> shuffledNumbers = NumberRandomShuffle.shuffle(lottoNumbers);
        return shuffledNumbers.subList(LOTTO_FROM_INDEX, LOTTO_TO_INDEX);
    }
}
