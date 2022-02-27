package lotto.domain.lotto;

import java.util.List;
import lotto.domain.LottoConstant;

public class LottoRange {
    private static final int LOTTO_MIN_RANGE = 1;
    private static final int LOTTO_MAX_RANGE = 45;

    public static void setRangeLottoNumber(final List<Integer> values) {
        for (int i = LOTTO_MIN_RANGE; i <= LOTTO_MAX_RANGE; i++) {
            values.add(i);
        }
    }
}
