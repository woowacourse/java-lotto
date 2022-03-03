package lotto.domain.ball;

import java.util.Objects;

public class Ball {

    private final int number;

    Ball(final int number) {
        BallValidator.validateBallNumber(number);
        this.number = number;
    }

    public int getBallNumber() {
        return this.number;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Ball ball = (Ball) object;
        return number == ball.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

}
