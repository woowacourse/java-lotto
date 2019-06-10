package lotto.domain.customcreatelotto;

import lotto.domain.LottoNumber;

import java.util.List;

/**
 * @author heebg
 * @version 1.0 2019-05-31
 */
public interface CustomCreateLotto {
    List<LottoNumber> custom(List<Integer> noFormedLotto);
}
