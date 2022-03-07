package lotterymachine.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lotterymachine.vo.Ball;

public class LotteryTicket {
    private static final int TICKET_SIZE = 6;
    private static final String INVALID_SIZE_EXCEPTION = "로또 숫자는 여섯개를 입력해야합니다.";
    private static final String DUPLICATE_NUMBER_EXCEPTION = "중복된 숫자가 입력되었습니다.";

    private final Set<Ball> balls;

    public LotteryTicket(final List<Ball> balls) {
        validateSize(balls);
        this.balls = new HashSet<>(balls);
        validateDuplication(balls);
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
            throw new IllegalArgumentException(INVALID_SIZE_EXCEPTION);
        }
    }

    private void validateDuplication(List<Ball> balls) {
        if (this.balls.size() != balls.size()) {
            throw new IllegalArgumentException(DUPLICATE_NUMBER_EXCEPTION);
        }
    }
}
