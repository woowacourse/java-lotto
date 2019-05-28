package lotto.utils;

import lotto.domain.LottoBall;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNoGenerator {
    private static final int COUNT_OF_NO = 6;
    private static final int BASE_INDEX = 0;
    private static final List<LottoBall> lottoNoContainer = new ArrayList<>();

    static {
        for (int i = LottoBall.MIN_NO; i <= LottoBall.MAX_NO; i++) {
            lottoNoContainer.add(new LottoBall(i));
        }
    }

    public static List<LottoBall> generate() {
        Collections.shuffle(lottoNoContainer);
        List<LottoBall> lotto = lottoNoContainer.subList(BASE_INDEX, COUNT_OF_NO);
        lotto.sort(LottoBall::compareTo);
        return lotto;
    }
}