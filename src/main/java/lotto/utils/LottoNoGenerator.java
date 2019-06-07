package lotto.utils;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNoGenerator {
    private static final int BASE_INDEX = 0;
    private static final List<LottoNo> lottoNoContainer = new ArrayList<>();

    static {
        for (int i = LottoNo.MIN_NO; i <= LottoNo.MAX_NO; i++) {
            lottoNoContainer.add(new LottoNo(i));
        }
    }

    public static List<LottoNo> generate() {
        ArrayList<LottoNo> copy = new ArrayList<>(lottoNoContainer);
        Collections.shuffle(copy);
        List<LottoNo> lotto = copy.subList(BASE_INDEX, Lotto.COUNT_OF_NO);
        lotto.sort(LottoNo::compareTo);
        return lotto;
    }
}