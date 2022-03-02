package lotto.model.prize;

import lotto.model.AutoLotto;
import lotto.model.number.BonusBall;
import lotto.model.number.WinningBalls;

public class MatchResult {
	private final int count;
	private final boolean bonus;

	private MatchResult(int count, boolean bonus) {
		this.count = count;
		this.bonus = bonus;
	}

	public static MatchResult of(AutoLotto autoLotto, WinningBalls winningBalls, BonusBall bonusBall) {
		return new MatchResult(autoLotto.match(winningBalls), bonusBall.match(autoLotto));
	}

	public boolean isCount(int count) {
		return this.count == count;
	}

	public boolean isBonus() {
		return bonus;
	}
}
