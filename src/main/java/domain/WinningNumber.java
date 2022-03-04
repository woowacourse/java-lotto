package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WinningNumber {
	private static final String BONUS_BALL_DUPLICATE_ERROR = "당첨 번호와 보너스 볼은 중복돼서는 안됩니다.";
	private static final String WINNING_NUMBER_DUPLICATE_ERROR = "당첨 번호는 중복돼서는 안됩니다.";

	private final List<Ball> winningBalls;
	private final Ball bonusBall;

	public WinningNumber(final List<Integer> winningBalls, final int bonusBall) {
		validateWinningNumber(winningBalls, bonusBall);

		this.winningBalls = winningBalls.stream()
			.map(Ball::from)
			.collect(Collectors.toUnmodifiableList());

		this.bonusBall = Ball.from(bonusBall);
	}

	private void validateWinningNumber(final List<Integer> winningBalls, final int bonusBall) {
		Ticket.validateBalls(winningBalls);
		validateDuplicate(winningBalls, bonusBall);
	}

	private void validateDuplicate(final List<Integer> winningBalls, final int bonusBall) {
		List<Integer> duplicateChecker = winningBalls.stream()
			.distinct().collect(Collectors.toUnmodifiableList());

		if (!winningBalls.equals(duplicateChecker)) {
			throw new IllegalArgumentException(WINNING_NUMBER_DUPLICATE_ERROR);
		}

		if (winningBalls.contains(bonusBall)) {
			throw new IllegalArgumentException(BONUS_BALL_DUPLICATE_ERROR);
		}
	}

	public List<Ball> getWinningBalls() {
		return Collections.unmodifiableList(winningBalls);
	}

	public Ball getBonusBall() {
		return bonusBall;
	}
}
