package utils;

import domain.lotto.LottoBall;
import java.util.Collections;
import java.util.List;

public class LottoRandomGenerator {
    private static final int END_INDEX = 6;
    private static final int FROM_INDEX = 0;

    public static List<LottoBall> generate() {
        Collections.shuffle(LottoBall.BALLS_CACHE);
        return LottoBall.BALLS_CACHE.subList(FROM_INDEX, END_INDEX);
    }
}
