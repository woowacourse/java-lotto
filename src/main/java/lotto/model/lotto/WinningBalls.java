package lotto.model.lotto;

import java.util.List;

public class WinningBalls {

	private final Lotto winningBalls;

	private WinningBalls(Lotto winningBalls) {
		this.winningBalls = winningBalls;
	}

	public static WinningBalls from(List<String> inputs) {
		return new WinningBalls(new Lotto(Lotto.generateManual(inputs)));
	}

	public boolean match(LottoBall lottoBall) {
		return winningBalls.contains(lottoBall);
	}
}
