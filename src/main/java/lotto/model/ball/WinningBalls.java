package lotto.model.ball;

import java.util.List;
import java.util.stream.Collectors;

public class WinningBalls {
	private static final String ERROR_SIZE = "[ERROR] 당첨 번호는 6개여야 합니다";
	private static final String ERROR_DUPLICATE = "[ERROR] 당첨 번호는 중복되면 안됩니다";
	private static final int WINNING_NUMBERS_SIZE = 6;

	private final List<LottoBall> winningBalls;

	private WinningBalls(List<LottoBall> winningBalls) {
		validate(winningBalls);
		this.winningBalls = winningBalls;
	}

	private void validate(List<LottoBall> winningBalls) {
		checkSize(winningBalls);
		checkDuplicate(winningBalls);
	}

	private void checkSize(List<LottoBall> winningBalls) {
		if (winningBalls.size() != WINNING_NUMBERS_SIZE) {
			throw new IllegalArgumentException(ERROR_SIZE);
		}
	}

	private void checkDuplicate(List<LottoBall> winningBalls) {
		if (getDistinctCount(winningBalls) != winningBalls.size()) {
			throw new IllegalArgumentException(ERROR_DUPLICATE);
		}
	}

	private int getDistinctCount(List<LottoBall> winningBalls) {
		return (int)winningBalls.stream()
				.distinct()
				.count();
	}

	public static WinningBalls from(List<String> inputs) {
		List<LottoBall> winningBalls = inputs.stream()
				.map(LottoBall::from)
				.collect(Collectors.toList());
		return new WinningBalls(winningBalls);
	}

	public boolean match(LottoBall lottoBall) {
		return winningBalls.stream().anyMatch(winningBall -> winningBall.match(lottoBall));
	}
}
