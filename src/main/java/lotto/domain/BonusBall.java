package lotto.domain;

import java.util.List;

public class BonusBall {
	private int bonusBall;

	public BonusBall(String bonusBall) {
		// 검증
		this.bonusBall = Integer.valueOf(bonusBall);
	}

	public boolean isMatchBonusBall(List<Integer> numbers) {
		return numbers.contains(this.bonusBall);
	}
}
