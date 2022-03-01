package lotto.domain.ticket;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.ball.Ball;
import lotto.domain.ball.BallStorage;
import lotto.domain.ticket.validation.TicketValidator;

public class Ticket {

    private final List<Ball> balls;

    public Ticket(final List<Integer> numbers) {
        TicketValidator.validateTicket(numbers);
        this.balls = generateBalls(sortNumbers(numbers));
    }

    private List<Integer> sortNumbers(final List<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .collect(Collectors.toUnmodifiableList());
    }

    private List<Ball> generateBalls(final List<Integer> numbers) {
        return numbers.stream()
                .map(BallStorage::getBall)
                .collect(Collectors.toUnmodifiableList());
    }

    public boolean contains(final Ball ball) {
        return balls.contains(ball);
    }

    public int countMatches(final Ticket ticket) {
        return (int) this.balls.stream()
                .filter(ticket::contains)
                .count();
    }

    public List<Integer> getBallNumbers() {
        return balls.stream()
                .map(Ball::getBallNumber)
                .collect(Collectors.toUnmodifiableList());
    }

}
