package lotto.domain;

import lotto.exception.LottoCustomException;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

public class LottoNumber {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private static final Map<Integer, LottoNumber> lottoNumbers = new HashMap<>();
    static {
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER ; i++) {
            lottoNumbers.put(i, new LottoNumber(i));
        }
    }

    private final int number;

    private LottoNumber(final int number) {
        this.number = number;
    }

    public static LottoNumber from(int number) {
        validate(number);
        return lottoNumbers.get(number);
    }

    public Integer getNumber() {
        return number;
    }

    private static void validate(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new LottoCustomException(format(
                    "로또 번호는 %d-%d사이의 숫자이어야 합니다",
                    MIN_LOTTO_NUMBER,
                    MAX_LOTTO_NUMBER));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber lottoNumber = (LottoNumber) o;
        return number == lottoNumber.number;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(number);
    }

}
