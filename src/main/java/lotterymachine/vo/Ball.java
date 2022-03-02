package lotterymachine.vo;

import static lotterymachine.model.ErrorMessage.OUT_OF_RANGE;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Ball {
    private static final int MINIMUM = 1;
    private static final int MAXIMUM = 45;
    private final int number;

    private Ball(int number) {
        validateRange(number);
        this.number = number;
    }

    public static Ball from(int number) {
        return new Ball(number);
    }

    public static List<Ball> createBalls(List<Integer> numbers) {
        return numbers.stream()
                .map(Ball::new)
                .collect(Collectors.toUnmodifiableList());
    }

    private void validateRange(int number) {
        if (number < MINIMUM || MAXIMUM < number) {
            throw new IllegalArgumentException(OUT_OF_RANGE.getMessage());
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
