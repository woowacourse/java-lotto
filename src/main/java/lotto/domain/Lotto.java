package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    private static final String BALL_COUNT_EXCEPTION_MESSAGE = "로또 볼의 갯수가 적절하지 않습니다.";
    private static final String SPLIT_DELIMITER = ", ";
    static final int BALL_COUNT = 6;

    private final Set<Ball> balls;

    public Lotto(Set<Ball> balls) {
        validateBallCount(balls);
        this.balls = Collections.unmodifiableSet(new HashSet<>(balls));
    }

    private void validateBallCount(Set<Ball> balls) {
        if (balls.size() != BALL_COUNT) {
            throw new IllegalArgumentException(BALL_COUNT_EXCEPTION_MESSAGE);
        }
    }

    public static Lotto of(String rawWinningLotto) {
        String[] numbers = rawWinningLotto.split(SPLIT_DELIMITER);
        Set<Ball> balls = new HashSet<>();
        for (String number : numbers) {
            balls.add(Ball.of(Integer.parseInt(number)));
        }
        return new Lotto(balls);
    }

    public boolean contains(Ball ball) {
        return balls.contains(ball);
    }

    public int countCommonBalls(Lotto lotto) {
        Set<Ball> sameBalls = new HashSet<>(balls);
        sameBalls.retainAll(lotto.balls);
        return sameBalls.size();
    }

    public List<String> getLotto() {
        List<String> ballsData = balls.stream()
            .map(Ball::toString)
            .collect(Collectors.toList());
        return Collections.unmodifiableList(ballsData);
    }
}
