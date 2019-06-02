package lotto.domain.customlotto;

import lotto.domain.CustomLotto;
import lotto.domain.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author heebg
 * @version 1.0 2019-05-31
 */
public class DefaultCustomLotto implements CustomLotto {

    @Override
    public List<LottoNumber> custom(List<Integer> noFormedLotto) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (Integer formedLotto : noFormedLotto) {
            lottoNumbers.add(new LottoNumber(formedLotto));
        }
        Collections.sort(lottoNumbers);
        return lottoNumbers;
    }
}
