package lotterymachine.vo;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Ball {
    private final int number;

    private Ball(int number) {
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
