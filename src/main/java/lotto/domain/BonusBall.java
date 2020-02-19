package lotto.domain;

import java.util.List;

public class BonusBall {
	private int bonusBall;

	public BonusBall(int bonusBall) {
		// 검증
		this.bonusBall = bonusBall;
	}

	public boolean isMatchBonusBall(List<Integer> numbers) {
		return numbers.contains(this.bonusBall);
	}
}
