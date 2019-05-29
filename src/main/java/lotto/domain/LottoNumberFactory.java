package lotto.domain;

import java.util.*;

public class LottoNumberFactory {
    static final int MAX_LOTTO_NUMBER = 45;
    static final int MIN_LOTTO_NUMBER = 1;

    public static List<LottoNumber> generateLottoNumbers() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            lottoNumbers.add(generateLottoNumber(i));
        }
        return lottoNumbers;
    }

    private static LottoNumber generateLottoNumber(int number) {
        return new LottoNumber(number);
    }
}
