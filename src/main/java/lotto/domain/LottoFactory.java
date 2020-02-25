package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoFactory {

    private static final int FROM_INDEX = 0;
    private static final int TO_INDEX = 6;
    public static final String INVALID_BALL_COUNT_EXCEPTION_MESSAGE
        = "로또가 가지고 있는 번호의 개수(%d)와 입력한 번호의 개수(%d)가 다릅니다.";
    public static final String SPLIT_DELIMITER = ", ";

    public static Lotto createRandom() {
        return new Lotto(createRandomBalls());
    }

    private static Set<Ball> createRandomBalls() {
        List<Ball> balls = Ball.getBalls();
        Collections.shuffle(balls);

        Set<Ball> randomBalls = new HashSet<>(balls.subList(FROM_INDEX, TO_INDEX));
        return randomBalls;
    }

    public static Lotto createManual(String balls) {
        Set<Ball> manualBall = new HashSet<>();
        putManualBall(balls, manualBall);
        validBallSize(manualBall);
        return new Lotto(manualBall);
    }

    private static void validBallSize(Set<Ball> manualBall) {
        if (manualBall.size() != Lotto.BALL_COUNT) {
            throw new IllegalArgumentException(String
                .format(INVALID_BALL_COUNT_EXCEPTION_MESSAGE, Lotto.BALL_COUNT, manualBall.size()));
        }
    }

    private static void putManualBall(String balls, Set<Ball> manualBall) {
        for (String ball : balls.split(SPLIT_DELIMITER)) {
            int ballNumber = Integer.parseInt(ball);
            manualBall.add(Ball.of(ballNumber));
        }
    }
}
