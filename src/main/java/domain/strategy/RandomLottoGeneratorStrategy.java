package domain.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.Lotto;
import domain.LottoNumber;

public class RandomLottoGeneratorStrategy implements LottoGeneratorStrategy {

    private List<LottoNumber> lottoNumbers;

    @Override
    public Lotto generate() {
        lottoNumbers = new ArrayList<>(LottoNumber.getLottoNumbers());
        Collections.shuffle(lottoNumbers);

        return Lotto.fromLotto(lottoNumbers);
    }
}
