package lotto.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private static final int BALLS_COUNT = 6;
    private static final int WINNING_BALLS_COUNT = 6;

    private final List<Ball> balls;

    public Lotto(List<Ball> balls) {
        checkEmpty(balls);
        this.balls = balls;
    }

    private void checkEmpty(List<Ball> balls) {
        if (balls.isEmpty()) {
            throw new RuntimeException("Lotto 내부의 Ball이 존재하지 않습니다.");
        }
    }

    public static int countsOfDuplicates(Lotto lotto1, Lotto lotto2) {
        Set<Ball> balls = new HashSet<>(lotto1.getBalls());
        balls.addAll(lotto2.getBalls());
        int differentNumbersCount = balls.size();
        return BALLS_COUNT + WINNING_BALLS_COUNT - differentNumbersCount;
    }

    public boolean isDuplicated() {
        Set<Ball> numbers = new HashSet<>(balls);
        return numbers.size() != balls.size();
    }

    public boolean contains(String bonusBall) {
        return this.balls.stream()
                .anyMatch(ball -> ball.getNumber() == Integer.parseInt(bonusBall));
    }

    public List<Ball> getBalls() {
        return Collections.unmodifiableList(balls);
    }
}
