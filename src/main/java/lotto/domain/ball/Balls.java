package lotto.domain.ball;

import java.util.HashMap;
import java.util.Map;

public class Balls {

    private static final Map<Integer, Ball> balls = new HashMap<>();

    public static Ball getBall(final int ballNumber) {
        saveBallIfNotExist(ballNumber);
        return balls.get(ballNumber);
    }

    private static void saveBallIfNotExist(final int ballNumber) {
        if (balls.containsKey(ballNumber)) {
            return;
        }
        balls.put(ballNumber, new Ball(ballNumber));
    }

}
