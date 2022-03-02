package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ball {
    public static final int MINIMUM_NUMBER = 1;
    public static final int MAXIMUM_NUMBER = 45;
    private static final String ERROR_LOTTO_NUMBER = "로또 숫자가 아닙니다";
    public static final List<Ball> cacheBalls = new ArrayList<>();

    private final int number;

    static {
        cacheBalls.addAll(IntStream.range(MINIMUM_NUMBER, MAXIMUM_NUMBER + 1)
                .mapToObj(Ball::new)
                .collect(Collectors.toList()));
    }

    public Ball(final int number) {
        validateLottoNumber(number);
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }

    public static List<Ball> getTotalBalls() {
        return Collections.unmodifiableList(cacheBalls);
    }

    private void validateLottoNumber(final int number) {
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
}
