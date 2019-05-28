package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LottoNumbersGenerator {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int SUBLIST_LOWER_BOUND = 0;
    private static final int SUBLIST_UPPER_BOUND = 6;
    private static final List<LottoNumber> totalLottoNumbers = new ArrayList<>();

    static {
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            totalLottoNumbers.add(new LottoNumber(i));
        }
    }

    public static LottoNumbers getLottoNumbers() {
        Collections.shuffle(totalLottoNumbers, new Random());
        ArrayList<LottoNumber> lottoNumbers = new ArrayList<>(
                totalLottoNumbers.subList(SUBLIST_LOWER_BOUND , SUBLIST_UPPER_BOUND));
        return new LottoNumbers(lottoNumbers);
    }
}
