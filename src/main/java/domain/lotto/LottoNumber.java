package domain.lotto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import utils.Constants;
import validator.LottoNumberValidator;

public class LottoNumber implements Comparable<LottoNumber> {
    public static final Map<Integer, LottoNumber> LOTTO_NUMBER_CACHE = new HashMap<>();

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
        LottoNumberValidator.validate(num);
        return LOTTO_NUMBER_CACHE.get(num);
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