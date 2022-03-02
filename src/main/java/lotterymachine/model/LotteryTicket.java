package lotterymachine.model;

import static lotterymachine.model.ErrorMessage.DUPLICATE_NUMBER;
import static lotterymachine.model.ErrorMessage.INVALID_SIZE;

import java.util.List;
import java.util.stream.Collectors;
import lotterymachine.vo.Ball;

public class LotteryTicket {
    private static final int TICKET_SIZE = 6;

    private final List<Ball> balls;

    public LotteryTicket(final List<Ball> balls) {
        validateSize(balls);
        validateDuplication(balls);
        this.balls = balls;
    }

    public int countMatchingBalls(LotteryTicket ticket) {
        return (int) this.balls.stream()
                .filter(ticket.balls::contains)
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

    private void validateSize(List<Ball> balls) {
        if (balls.size() != TICKET_SIZE) {
            throw new IllegalArgumentException(INVALID_SIZE.getMessage());
        }
    }

    private void validateDuplication(List<Ball> balls) {
        if (balls.stream().distinct().count() != balls.size()) {
            throw new IllegalArgumentException(DUPLICATE_NUMBER.getMessage());
        }
    }
}
