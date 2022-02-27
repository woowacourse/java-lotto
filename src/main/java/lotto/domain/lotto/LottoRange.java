package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoRange {
    private static final int LOTTO_MIN_RANGE = 1;
    private static final int LOTTO_MAX_RANGE = 45;

    public static List<Integer> getRangeLottoNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = LOTTO_MIN_RANGE; i <= LOTTO_MAX_RANGE; i++) {
            numbers.add(i);
        }
        return numbers;
    }
}
