package lotto.domain.ball;

public class Ball {

    private final int number;

    public Ball(final int number) {
        BallValidator.validateBallNumber(number);
        this.number = number;
    }

    public int getBallNumber() {
        return this.number;
    }

}
