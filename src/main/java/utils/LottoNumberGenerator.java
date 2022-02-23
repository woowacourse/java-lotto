package utils;

import java.util.Collections;
import java.util.List;

public class LottoNumberGenerator {

    public static final int END_INDEX = 6;
    public static final int FROM_INDEX = 0;

    public static List<Integer> generate() {
        Collections.shuffle(Constants.LOTTO_NUMS);
        return Constants.LOTTO_NUMS.subList(FROM_INDEX, END_INDEX);
    }
}
