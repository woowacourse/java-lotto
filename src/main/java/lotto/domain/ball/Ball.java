package lotto.domain.ball;

import lotto.domain.ball.validation.BallValidator;

public class Ball {

    private final int number;

    Ball(final int number) {
        BallValidator.validateBallNumber(number);
        this.number = number;
    }

    public int getBallNumber() {
        return this.number;
    }

}
