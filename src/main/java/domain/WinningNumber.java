package domain;

import java.util.List;
import java.util.stream.Collectors;

public class WinningNumber {
	private static final int LOTTO_SIZE = 6;

	private static final String SIZE_ERROR = "당첨 번호는 6자리여야 합니다.";
	private static final String BONUS_BALL_DUPLICATE_ERROR = "당첨 번호와 보너스 볼은 중복돼서는 안됩니다.";
	private static final String WINNING_NUMBER_DUPLICATE_ERROR = "당첨 번호는 중복돼서는 안됩니다.";

	private final List<Ball> winningBalls;
	private final Ball bonusBall;

	public WinningNumber(List<Integer> winningBalls, int bonusBall) {
		validateWinningNumber(winningBalls, bonusBall);

		this.winningBalls = winningBalls.stream()
			.map(Ball::new)
			.collect(Collectors.toUnmodifiableList());

		this.bonusBall = new Ball(bonusBall);
	}

	private void validateWinningNumber(List<Integer> winningBalls, int bonusBall) {
		if (winningBalls.size() != LOTTO_SIZE) {
			throw new IllegalArgumentException(SIZE_ERROR);
		}

		validateDuplicate(winningBalls, bonusBall);
	}

	private void validateDuplicate(List<Integer> winningBalls, int bonusBall) {
		List<Integer> duplicateChecker = winningBalls.stream()
			.distinct().collect(Collectors.toUnmodifiableList());

		if(winningBalls.equals(duplicateChecker)) {
			throw new IllegalArgumentException(WINNING_NUMBER_DUPLICATE_ERROR);
		}

		if (winningBalls.contains(bonusBall)) {
			throw new IllegalArgumentException(BONUS_BALL_DUPLICATE_ERROR);
		}
	}

	public List<Ball> getWinningBalls() {
		return winningBalls;
	}

	public Ball getBonusBall() {
		return bonusBall;
	}
}