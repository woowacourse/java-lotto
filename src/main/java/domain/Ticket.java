package domain;

import java.util.List;
import java.util.stream.Collectors;

public class Ticket {
	public static final int PRICE = 1000;
	public static final int SIZE = 6;
	private static final int CONDITION_FOR_CHECK_BONUS_BALL = 5;

	public static final String SIZE_ERROR = "로또 번호는 6자리여야 합니다.";

	private final List<Ball> balls;

	public Ticket(final List<Integer> numbers) {
		validateBalls(numbers);
		this.balls = makeNumbersToBalls(numbers);
	}

	public static void validateBalls(List<Integer> numbers) {
		if (numbers.size() != SIZE) {
			throw new IllegalArgumentException(SIZE_ERROR);
		}
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

	public List<String> makeBallsToStrings() {
		return balls.stream()
			.map(Object::toString)
			.collect(Collectors.toList());
	}

	public List<Ball> getBalls() {
		return balls;
	}
}
