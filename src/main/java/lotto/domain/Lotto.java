package lotto.domain;

import java.util.Collections;
import java.util.List;

public class Lotto {
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

    public List<Ball> getBalls() {
        return Collections.unmodifiableList(balls);
    }
}
