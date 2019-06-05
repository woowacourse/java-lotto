package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoGenerator {
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int LOTTO_SIZE = 6;

    private static List<LottoNumber> allLottoNumbers;

    static {
        allLottoNumbers = new ArrayList<>();
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            allLottoNumbers.add(new LottoNumber(i));
        }
    }

    static Lotto generate() {
        Collections.shuffle(allLottoNumbers);
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = 0; i < LOTTO_SIZE; i++) {
            lottoNumbers.add(allLottoNumbers.get(i));
        }
        Collections.sort(lottoNumbers);

        return new Lotto(lottoNumbers);
    }
}
