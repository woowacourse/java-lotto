package lotto.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RandomLottoGenerator implements LottoGenerator {

    @Override
    public Set<LottoNumber> generateNumbers() {
        List<LottoNumber> lottoNumbers = LottoFactory.getLottoNumbers();
        Set<LottoNumber> lotto = new HashSet<>();
        Collections.shuffle(lottoNumbers);
        for (int i = 0; i < LOTTO_LENGTH; i++) {
            lotto.add(lottoNumbers.get(i));
        }
        return lotto;
    }
}
