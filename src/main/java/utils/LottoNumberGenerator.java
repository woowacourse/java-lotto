package utils;

import java.util.Collections;
import java.util.List;

public class LottoNumberGenerator {
    public static List<Integer> generate() {
        Collections.shuffle(Constants.LOTTO_NUMS);
        return Constants.LOTTO_NUMS.subList(0,6);
    }
}
