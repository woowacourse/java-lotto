package lotto.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RandomLottoFactory implements LottoFactory {

    private static final int FROM_INDEX = 0;
    private static final int TO_INDEX = 6;

    @Override
    public Lotto create() {
        return new Lotto(createRandomBalls());
    }

    private Set<Ball> createRandomBalls() {
        List<Ball> balls = Ball.getBalls();
        Collections.shuffle(balls);

        Set<Ball> randomBalls = new HashSet<>(balls.subList(FROM_INDEX, TO_INDEX));
        return randomBalls;
    }
}
