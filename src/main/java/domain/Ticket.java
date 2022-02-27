package domain;

import java.util.List;
import java.util.stream.Collectors;

public class Ticket {
	public static final int PRICE = 1000;
	public static final int SIZE = 6;
	private static final int CONDITION_FOR_CHECK_BONUS_BALL = 5;

	public static final String SIZE_ERROR = "당첨 번호는 6자리여야 합니다.";

	private static final String START_SIGN = "[";
	private static final String END_SIGN = "]";
	private static final String BALL_DELIMITER = ",";
	private static final String LINE_DELIMITER = "\n";

	private final List<Ball> balls;

	public Ticket(final List<Integer> numbers) {
		this.balls = makeNumbersToBalls(numbers);
	}

	private List<Ball> makeNumbersToBalls(List<Integer> numbers) {
		validateBalls(numbers);

		return numbers.stream()
			.map(Ball::new)
			.collect(Collectors.toUnmodifiableList());
	}

	public static void validateBalls(List<Integer> numbers) {
		if (numbers.size() != SIZE) {
			throw new IllegalArgumentException(SIZE_ERROR);
		}
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

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(START_SIGN);
		stringBuilder.append(String.join(BALL_DELIMITER, balls.stream()
			.map(Ball::toString)
			.collect(Collectors.toUnmodifiableList())));
		stringBuilder.append(END_SIGN);
		stringBuilder.append(LINE_DELIMITER);

		return stringBuilder.toString();
	}
}
