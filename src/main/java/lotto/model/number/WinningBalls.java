package lotto.model.number;

import java.util.List;
import java.util.stream.Collectors;

public class WinningBalls {
	private static final String ERROR_SIZE = "[ERROR] 당첨 번호는 6개여야 합니다";
	private static final String ERROR_DUPLICATE = "[ERROR] 당첨 번호는 중복되면 안됩니다";
	private static final int WINNING_NUMBERS_SIZE = 6;

	private final List<WinningBall> winningBalls;

	private WinningBalls(List<WinningBall> winningBalls) {
		validate(winningBalls);
		this.winningBalls = winningBalls;
	}

	private void validate(List<WinningBall> winningBalls) {
		checkSize(winningBalls);
		checkDuplicate(winningBalls);
	}

	private void checkSize(List<WinningBall> winningBalls) {
		if (winningBalls.size() != WINNING_NUMBERS_SIZE) {
			throw new IllegalArgumentException(ERROR_SIZE);
		}
	}

	private void checkDuplicate(List<WinningBall> winningBalls) {
		if (getDistinctCount(winningBalls) != winningBalls.size()) {
			throw new IllegalArgumentException(ERROR_DUPLICATE);
		}
	}

	public static WinningBalls from(List<String> inputs) {
		List<WinningBall> winningBalls = inputs.stream()
				.map(WinningBall::from)
				.collect(Collectors.toList());
		return new WinningBalls(winningBalls);
	}

	private int getDistinctCount(List<WinningBall> winningBalls) {
		return (int)winningBalls.stream()
				.distinct()
				.count();
	}

	public boolean match(LottoBall lottoBall) {
		return winningBalls.stream().anyMatch(winningBall -> winningBall.match(lottoBall.getNumber()));
	}
}
