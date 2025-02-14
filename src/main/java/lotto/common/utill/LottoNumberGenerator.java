package lotto.common.utill;

import static lotto.common.constant.Constant.*;

import java.util.ArrayList;
import java.util.List;

public final class LottoNumberGenerator {

    public static List<Integer> generate() {
        List<Integer> list = new ArrayList<>();

        while (list.size() < LOTTO_NUMBER_COUNT) {
            int randomInt = (int)(Math.random() * LOTTO_RANGE_MAXIMUM) + LOTTO_RANGE_MINIMUM;
            if (!list.contains(randomInt)) {
                list.add(randomInt);
            }
        }

        return list;
    }

}
