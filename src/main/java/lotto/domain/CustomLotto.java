package lotto.domain;

import java.util.List;

/**
 * @author heebg
 * @version 1.0 2019-05-31
 */
public interface CustomLotto {
    List<LottoNumber> custom(List<Integer> noFormedLotto);
}
