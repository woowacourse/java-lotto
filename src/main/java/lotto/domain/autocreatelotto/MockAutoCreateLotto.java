package lotto.domain.autocreatelotto;

import lotto.domain.LottoNumber;
import lotto.domain.AutoCreateLotto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author heebg
 * @version 1.0 2019-05-31
 */
public class MockAutoCreateLotto implements AutoCreateLotto {
    private static final int MAX_LOTTO_SIZE = 6;

    @Override
    public List<LottoNumber> autoCreate() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= MAX_LOTTO_SIZE; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
        return lottoNumbers;
    }
}
