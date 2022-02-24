package lotto.domain.ticket;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lotto.domain.rank.Rank;
import lotto.domain.ticket.validator.TicketValidator;

public class Ticket {

    private final List<Ball> balls;

    public Ticket(final List<Integer> numbers) {
        TicketValidator.validateTicket(numbers);
        this.balls = generateBalls(numbers);
    }

    private List<Ball> generateBalls(final List<Integer> numbers) {
        return numbers.stream()
                .map(Balls::findBall)
                .collect(Collectors.toUnmodifiableList());
    }

    public boolean contains(final Ball ball) {
        return balls.contains(ball);
    }

    public int countMatches(final List<Ball> anotherBalls) {
        return (int) this.balls.stream()
                .filter(anotherBalls::contains)
                .count();
    }

    public Optional<Rank> calculateRank(final Ticket ticket, final Ball bonusBall) {
        final int matchCount = ticket.countMatches(this.balls);
        final boolean bonusBallMatched = this.balls.contains(bonusBall);
        return Rank.of(matchCount, bonusBallMatched);
    }

    public List<Integer> getBallNumbers() {
        return balls.stream()
                .map(Ball::getBallNumber)
                .collect(Collectors.toUnmodifiableList());
    }

}
