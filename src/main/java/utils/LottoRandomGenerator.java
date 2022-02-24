package utils;

import domain.LottoBall;
import java.util.Collections;
import java.util.List;

public class LottoRandomGenerator {

    public static final int END_INDEX = 6;
    public static final int FROM_INDEX = 0;

    public static List<LottoBall> generate() {
        Collections.shuffle(LottoBall.LOTTO_NUMBERS_CACHE);
        return LottoBall.LOTTO_NUMBERS_CACHE.subList(FROM_INDEX, END_INDEX);
    }
}
