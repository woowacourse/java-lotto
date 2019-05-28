package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LottoNumbers {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int SUBLIST_LOWER_BOUND = 0;
    private static final int SUBLIST_UPPER_BOUND = 6;
    private static final List<LottoNumber> lottoNumbers = new ArrayList<>();

    static {
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
    }

    public static List<LottoNumber> getLottoNumbers() {
        Collections.shuffle(lottoNumbers, new Random());
        return new ArrayList<>(lottoNumbers.subList(SUBLIST_LOWER_BOUND , SUBLIST_UPPER_BOUND));
    }
}
