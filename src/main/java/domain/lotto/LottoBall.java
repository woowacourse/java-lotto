package domain.lotto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import utils.Constants;
import validator.LottoBallValidator;

public class LottoBall implements Comparable<LottoBall> {
    public static final Map<Integer, LottoBall> BALLS_CACHE = new HashMap<>();

    static {
        for (int i = Constants.MIN_LOTTO_NUM; i <= Constants.MAX_LOTTO_NUM; i++) {
            BALLS_CACHE.put(i, new LottoBall(i));
        }
    }

    private final int number;

    private LottoBall(int number) {
        this.number = number;
    }

    public static LottoBall from(int num) {
        LottoBallValidator.validate(num);
        return BALLS_CACHE.get(num);
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
    public String toString() {
        return "LottoBall{" +
                "number=" + number +
                '}';
    }

    @Override
    public int compareTo(LottoBall o) {
        return java.lang.Integer.compare(number, o.get());
    }
}