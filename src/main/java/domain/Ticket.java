package domain;

import java.util.List;
import java.util.stream.Collectors;

public class Ticket {
	private static final int CONDITION_FOR_CHECK_BONUS_BALL = 5;

	private final List<Ball> balls;

	public Ticket(final List<Integer> numbers) {
		this.balls = makeNumbersToBalls(numbers);
	}

	private List<Ball> makeNumbersToBalls(List<Integer> numbers) {
		return numbers.stream()
			.map(Ball::new)
			.collect(Collectors.toUnmodifiableList());
	}

	public Rank getRank(WinningNumber winningNumber) {
		int matchCount = countMatch(winningNumber.getWinningBalls());
		boolean bonusBallMatched = false;

		if (matchCount == CONDITION_FOR_CHECK_BONUS_BALL) {
			bonusBallMatched = balls.contains(winningNumber.getBonusBall());
		}

		return Rank.of(matchCount, bonusBallMatched);
	}

	private int countMatch(List<Ball> balls) {
		return (int)balls.stream()
			.filter(this.balls::contains)
			.count();
	}
}
