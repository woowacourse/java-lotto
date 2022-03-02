package lotto.model.ball;

import lotto.model.Lotto;

public class BonusBall {
	private static final String ERROR_DUPLICATE = "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다";

	private final LottoBall bonusBall;

	private BonusBall(LottoBall lottoBall) {
		this.bonusBall = lottoBall;
	}

	public static BonusBall from(LottoBall lottoBall, WinningBalls winningBalls) {
		if (winningBalls.match(lottoBall)) {
			throw new IllegalArgumentException(ERROR_DUPLICATE);
		}
		return new BonusBall(lottoBall);
	}

	public boolean match(Lotto lotto) {
		return lotto.contains(this.bonusBall);
	}

}
