package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Ball implements Comparable<Ball> {
    private static final int MINIMUM_NUMBER = 1;
    private static final int MAXIMUM_NUMBER = 45;
    private static final String ERROR_LOTTO_NUMBER = "로또 숫자가 아닙니다";
    public static final Map<Integer, Ball> cacheBalls = new HashMap<>();

    private final int number;

    static {
        for (int i = MINIMUM_NUMBER; i <= MAXIMUM_NUMBER; i++) {
            cacheBalls.put(i, new Ball(i));
        }
    }

    private Ball(final int number) {
        validateLottoNumber(number);
        this.number = number;
    }

    public static Ball of(final int number) {
        if (cacheBalls.containsKey(number)) {
            return cacheBalls.get(number);
        }
        return new Ball(number);
    }

    public int getNumber() {
        return this.number;
    }

    public static List<Ball> getTotalBalls() {
        return List.copyOf(cacheBalls.values());
    }

    private static void validateLottoNumber(final int number) {
        if (number < MINIMUM_NUMBER || number > MAXIMUM_NUMBER) {
            throw new IllegalArgumentException(ERROR_LOTTO_NUMBER);
        }
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Ball)) {
            return false;
        }
        Ball ball = (Ball) obj;
        return this.number == ball.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public int compareTo(Ball otherBall) {
        return Integer.compare(this.number, otherBall.number);
    }
}
