package Lotto.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoNumber {
    private final static int MIN_LOTTO_NUMBER = 1;
    private final static int MAX_LOTTO_NUMBER = 45;
    private final static String OUT_OF_RANGE = "로또번호는 1~45의 범위입니다.";
    private final static Map<Integer, LottoNumber> numbers = new HashMap<>();

    private int lottoNumber;

    static {
        for (int i = MIN_LOTTO_NUMBER; i < MAX_LOTTO_NUMBER + 1; i++) {
            numbers.put(i, new LottoNumber(i));
        }
    }

    public LottoNumber(int number) {
        this.lottoNumber = number;
    }

    public static LottoNumber of(int number) {
        LottoNumber lottoNumber = numbers.get(number);
        if (lottoNumber == null) {
            throw new IllegalArgumentException(OUT_OF_RANGE);
        }
        return lottoNumber;
    }

    int getLottoNumber() {
        return lottoNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return lottoNumber == that.lottoNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumber);
    }
}
