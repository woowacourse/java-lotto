package lotterymachine.vo;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ball {
    private static final List<Ball> preparedNumbers = IntStream.rangeClosed(0, 45).boxed().map(Ball::new).collect(Collectors.toList());
    private static final int MINIMUM = 1;
    private static final int MAXIMUM = 45;
    private static final String OUT_OF_RANGE_EXCEPTION = "로또 숫자는 1이상 45이하여야 합니다.";

    private final int number;

    private Ball(int number) {
        this.number = number;
    }

    public static Ball from(int number) {
        validateRange(number);
        return preparedNumbers.get(number);
    }

    public static List<Ball> getBalls(List<Integer> numbers) {
        return numbers.stream()
                .map(Ball::from)
                .collect(Collectors.toUnmodifiableList());
    }

    private static void validateRange(int number) {
        if (number < MINIMUM || MAXIMUM < number) {
            throw new IllegalArgumentException(OUT_OF_RANGE_EXCEPTION);
        }
    }

    public int getNumber() {
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
        Ball ball = (Ball) o;
        return number == ball.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
