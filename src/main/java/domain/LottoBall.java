package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import utils.Constants;
import validator.LottoNumberValidator;

public class LottoBall implements Comparable<LottoBall> {
    public static final List<LottoBall> LOTTO_NUMBERS_CACHE = new ArrayList<>();

    static {
        LOTTO_NUMBERS_CACHE.add(null);
        for (int i = Constants.MIN_LOTTO_NUM; i <= Constants.MAX_LOTTO_NUM; i++) {
            LOTTO_NUMBERS_CACHE.add(new LottoBall(i));
        }
    }

    private final int number;

    private LottoBall(int number) {
        this.number = number;
    }

    public static LottoBall from(int num) {
        LottoNumberValidator.validate(num);
        return LOTTO_NUMBERS_CACHE.get(num);
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
        LottoBall that = (LottoBall) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public int compareTo(LottoBall o) {
        return java.lang.Integer.compare(number, o.get());
    }
}