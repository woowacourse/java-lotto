package lotto.domain.customcreatelotto;

import lotto.domain.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author heebg
 * @version 1.0 2019-05-31
 */
public class DefaultCustomCreateCreateLotto implements CustomCreateLotto {

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
