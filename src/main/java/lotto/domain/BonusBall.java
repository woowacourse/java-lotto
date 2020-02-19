package lotto.domain;

public class BonusBall {
	private int bonusBall;

	public BonusBall(String bonusBall) {
		// 검증
		this.bonusBall = Integer.valueOf(bonusBall);
	}

	public boolean isMatchBonusBall(Lotto lotto) {
		return lotto.isContain(this.bonusBall);
	}
}
