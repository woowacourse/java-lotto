package lotto.model.number;

import lotto.model.AutoLotto;

public class BonusBall {
	private static final String ERROR_DUPLICATE = "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다";

	private final LottoBall lottoBall;

	private BonusBall(LottoBall lottoBall) {
		this.lottoBall = lottoBall;
	}

	public static BonusBall from(LottoBall lottoBall, WinningBalls winningBalls) {
		if (winningBalls.match(lottoBall)) {
			throw new IllegalArgumentException(ERROR_DUPLICATE);
		}
		return new BonusBall(lottoBall);
	}

	public boolean match(AutoLotto autoLotto) {
		return autoLotto.contains(this.lottoBall);
	}

}
