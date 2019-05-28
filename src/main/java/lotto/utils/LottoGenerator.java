package lotto.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {
    private static final int COUNT_OF_NO = 6;
    private static final int MAX_NO = 45;
    private static final int MIN_NO = 1;
    private static final int BASE_INDEX = 0;
    private static final List<Integer> lottoNoContainer = new ArrayList<>();

    static {
        for (int i = MIN_NO; i <= MAX_NO; i++) {
            lottoNoContainer.add(i);
        }
    }

    public static List<Integer> generate() {
        Collections.shuffle(lottoNoContainer);
        List<Integer> lotto = lottoNoContainer.subList(BASE_INDEX, COUNT_OF_NO);
        Collections.sort(lotto);
        return lotto;
    }
}
