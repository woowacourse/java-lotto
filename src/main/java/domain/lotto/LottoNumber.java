package domain.lotto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import utils.Constants;

public class LottoNumber implements Comparable<LottoNumber> {
    public static final Map<Integer, LottoNumber> LOTTO_NUMBER_CACHE = new HashMap<>();
    public static final String LOTTO_NUM_RANGE_ERROR_MESSAGE = "로또 번호는 1에서 45사이의 수여야 합니다.";


    static {
        for (int i = Constants.MIN_LOTTO_NUM; i <= Constants.MAX_LOTTO_NUM; i++) {
            LOTTO_NUMBER_CACHE.put(i, new LottoNumber(i));
        }
    }

    private final int number;

    private LottoNumber(final int number) {
        this.number = number;
    }

    public static LottoNumber from(final int num) {
        validate(num);
        return LOTTO_NUMBER_CACHE.get(num);
    }

    private static void validate(final int number) {
        if (number < Constants.MIN_LOTTO_NUM || number > Constants.MAX_LOTTO_NUM) {
            throw new IllegalArgumentException(LOTTO_NUM_RANGE_ERROR_MESSAGE);
        }
    }

    public int get() {
        return this.number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "LottoNumber{" +
                "number=" + number +
                '}';
    }

    @Override
    public int compareTo(LottoNumber o) {
        return java.lang.Integer.compare(number, o.get());
    }
}