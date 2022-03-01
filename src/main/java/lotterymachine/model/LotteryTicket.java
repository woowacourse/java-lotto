package lotterymachine.model;

import java.util.List;
import java.util.stream.Collectors;
import lotterymachine.vo.Ball;

public class LotteryTicket {
    private final List<Ball> balls;

    public LotteryTicket(final List<Ball> balls) {
        this.balls = balls;
    }

    public int countMatchingBalls(List<Ball> balls) {
        return (int) balls.stream()
                .filter(this.balls::contains)
                .count();
    }

    public boolean contains(Ball ball) {
        return this.balls.contains(ball);
    }

    public List<Integer> getBalls() {
        return balls.stream()
                .map(Ball::getNumber)
                .collect(Collectors.toUnmodifiableList());
    }
}
