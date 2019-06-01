package lotto.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static lotto.domain.LottoRule.*;

public class LottoNumber implements Comparable<LottoNumber> {
    private final int number;

    private static Map<Integer, LottoNumber> bucket = new HashMap<>();

    static {
        for (int i = MIN_LOTTO_NUMBER.get(); i <= MAX_LOTTO_NUMBER.get(); ++i) {
            bucket.put(i, new LottoNumber(i));
        }
    }

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber getInstance(int number) {
        checkValidRange(number);
        return bucket.get(number);
    }

    private static void checkValidRange(int number) {
        if (number < MIN_LOTTO_NUMBER.get() || number > MAX_LOTTO_NUMBER.get()) {
            throw new IllegalArgumentException("유효한 로또 번호가 아닙니다.");
        }
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public int compareTo(LottoNumber lottoNumber) {
        return this.number - lottoNumber.number;
    }
}
