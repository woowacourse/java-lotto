package lotto.domain;

public class BonusBall {
	private final LottoNo bonusBall;

	public BonusBall(String bonusBall) {
		this.bonusBall = new LottoNo(bonusBall);
	}

	public boolean isContainBonusBall(Lotto lotto) {
		return lotto.isContain(this.bonusBall);
	}
}
