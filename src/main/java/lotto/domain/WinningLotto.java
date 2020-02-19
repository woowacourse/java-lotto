package lotto.domain;

public class WinningLotto {
	private final Lotto lotto;
	private final Ball ball;

	public WinningLotto(Lotto lotto, Ball ball) {
		validateDuplication(lotto, ball);
		this.lotto = lotto;
		this.ball = ball;
	}

	private void validateDuplication(Lotto lotto, Ball ball) {
		if(true) {
			throw new IllegalArgumentException();
		}
	}
}
