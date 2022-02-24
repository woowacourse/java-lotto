package lotto.domain.ticket;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import lotto.domain.ticket.condition.BallNumberRange;
import lotto.domain.ticket.validator.BallValidator;

public class Balls {

    private static final Map<Integer, Ball> balls = new HashMap<>();

    static {
        IntStream ballNumbers = BallNumberRange.getBallNumbers();
        ballNumbers.forEach(number -> balls.put(number, new Ball(number)));
    }

    public static Ball findBall(final int ballNumber) {
        BallValidator.validateBallNumber(ballNumber);
        return balls.get(ballNumber);
    }

}
