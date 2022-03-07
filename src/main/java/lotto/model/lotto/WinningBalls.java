package lotto.model.lotto;

import java.util.List;

import lotto.model.prize.Prize;

public class WinningBalls {
	private static final String ERROR_DUPLICATE = "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다";

	private final Lotto winningBalls;
	private final LottoBall bonusBall;

	private WinningBalls(Lotto winningBalls, LottoBall bonusBall) {
		this.winningBalls = winningBalls;
		this.bonusBall = bonusBall;
	}

	public static WinningBalls from(List<String> inputs, String input) {
		checkDuplicate(inputs, input);
		return new WinningBalls(Lotto.fromManual(inputs), LottoBall.from(input));
	}

	private static void checkDuplicate(List<String> inputs, String input) {
		if (inputs.contains(input)) {
			throw new IllegalArgumentException(ERROR_DUPLICATE);
		}
	}

	public Prize getPrize(Lotto lotto) {
		int winningCount = matchWinningBalls(lotto);
		boolean bonus = matchBonusBall(lotto);

		return Prize.getPrize(winningCount, bonus);
	}

	private int matchWinningBalls(Lotto lotto) {
		return lotto.match(this.winningBalls);
	}

	private boolean matchBonusBall(Lotto lotto) {
		return lotto.contains(this.bonusBall);
	}

}
