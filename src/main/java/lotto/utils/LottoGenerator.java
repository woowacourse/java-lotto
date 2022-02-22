package lotto.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {

    private static final int LOTTO_MINIMUM = 1;
    private static final int LOTTO_MAXIMUM = 45;
    private static final int FROM_LOTTO_INDEX = 0;
    private static final int TO_LOTTO_INDEX = 6;

    private static final List<Integer> originLottoNumbers = new ArrayList<>();

    static {
        for (int i = LOTTO_MINIMUM; i <= LOTTO_MAXIMUM; i++) {
            originLottoNumbers.add(i);
        }
    }

    public static List<Integer> generateLottoNumbers() {
        Collections.shuffle(originLottoNumbers);
        List<Integer> lottoNumbers = originLottoNumbers.subList(FROM_LOTTO_INDEX, TO_LOTTO_INDEX);
        Collections.sort(lottoNumbers);

        return lottoNumbers;
    }
}
