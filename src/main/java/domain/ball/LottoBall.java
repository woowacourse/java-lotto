package domain.ball;

import java.util.*;

public class LottoBall implements Comparable<LottoBall> {
    public static final int MIN_LOTTO_VALUE = 1;
    public static final int MAX_LOTTO_VALUE = 45;
    private static final String PERMIT_LOTTO_NUMBER_EXCEPTION_MESSAGE = "%d~%d 사이의 번호만 허용합니다.";
    private static final Map<Integer, LottoBall> lottoBalls = new HashMap<>();

    static {
        for (int i = MIN_LOTTO_VALUE; i <= MAX_LOTTO_VALUE; i++) {
            lottoBalls.put(i, new LottoBall(i));
        }
    }

    private final int value;

    private LottoBall(final int value) {
        this.value = value;
    }

    public static LottoBall from(final int value) {
        if (Objects.isNull(lottoBalls.get(value))) {
            throw new IllegalArgumentException(String.format(PERMIT_LOTTO_NUMBER_EXCEPTION_MESSAGE, MIN_LOTTO_VALUE, MAX_LOTTO_VALUE));
        }
        return lottoBalls.get(value);
    }

    public static List<LottoBall> getLottoBalls() {
        return new ArrayList<>(lottoBalls.values());
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoBall that = (LottoBall) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int compareTo(LottoBall o) {
        if (this.value > o.value) {
            return 1;
        }
        return -1;
    }

    @Override
    public String toString() {
        return value + " ";
    }
}
