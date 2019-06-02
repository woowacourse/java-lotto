package lotto.domain.makeuplotto;

import lotto.domain.LottoGenerateBase;
import lotto.domain.LottoNumber;
import lotto.domain.CreateLotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author heebg
 * @version 1.0 2019-05-31
 */
public class DefaultCreateLotto implements CreateLotto {

    private static final int MAX_LOTTO_SIZE = 6;

    @Override
    public List<LottoNumber> create() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        Collections.shuffle(LottoGenerateBase.lottoGenerateBase);
        for (int i = 0; i < MAX_LOTTO_SIZE; i++) {
            lottoNumbers.add(LottoGenerateBase.lottoGenerateBase.get(i));
        }
        Collections.sort(lottoNumbers);
        return lottoNumbers;
    }
}
