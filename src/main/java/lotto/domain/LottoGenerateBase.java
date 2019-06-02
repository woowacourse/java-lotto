package lotto.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author heebg
 * @version 1.0 2019-05-29
 */
public class LottoGenerateBase {
    private static final int MIN_RANGE = 1;
    private static final int MAX_RANGE = 45;
    public static final List<LottoNumber> lottoGenerateBase;

    static {
        lottoGenerateBase = new ArrayList<>();
        for (int i = MIN_RANGE; i <= MAX_RANGE; i++) {
            lottoGenerateBase.add(new LottoNumber(i));
        }
    }
}
