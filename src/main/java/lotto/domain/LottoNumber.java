package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoNumber {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private static final List<LottoNumber> totalLottoNumbers = new ArrayList<>();

    private final int number;

    static {
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            LottoNumber lottoNumber = new LottoNumber(i);
            totalLottoNumbers.add(lottoNumber);
        }
    }

    private LottoNumber(final int number) {
        this.number = number;
    }

    static LottoNumber valueOf(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("없는 로또 번호입니다.");
        }
        return totalLottoNumbers.get(number - 1);
    }

    int getNumber() {
        return number;
    }
}
