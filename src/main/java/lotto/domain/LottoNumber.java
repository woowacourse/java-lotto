package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoNumber {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private static final List<LottoNumber> numbers = new ArrayList<>();
    private final int number;

    static {
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            numbers.add(new LottoNumber(i));
        }
    }

    private LottoNumber(int lottoNumber) {
        this.number = lottoNumber;
    }

    public static LottoNumber get(int lottoNumber) {
        if (lottoNumber < MIN_LOTTO_NUMBER || lottoNumber > MAX_LOTTO_NUMBER) {
            throw new InvalidLottoNumber("로또 번호는 1 이상 45 이하여야 합니다.");
        }
        return numbers.get(lottoNumber);
    }
}
