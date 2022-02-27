package domain;

import java.util.List;
import java.util.stream.Collectors;

public class WinningNumber {
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
		if(winningBalls.size() != 6) {
			throw new IllegalArgumentException("당첨 번호는 6자리여야 합니다.");
		}

		if(winningBalls.contains(bonusBall)) {
			throw new IllegalArgumentException("당첨 번호와 보너스 볼은 중복돼서는 안됩니다.");
		}
	}

	public List<Ball> getWinningBalls() {
		return winningBalls;
	}

	public Ball getBonusBall() {
		return bonusBall;
	}
}