package lotto.domain;

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
