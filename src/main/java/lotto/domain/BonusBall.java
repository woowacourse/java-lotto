package lotto.domain;

import lotto.validator.Validator;

public class BonusBall {
	private final LottoNo bonusBall;

	public BonusBall(String bonusBall) {
		Validator.validateInteger(bonusBall);
		this.bonusBall = new LottoNo(Integer.parseInt(bonusBall));
	}

	public boolean isContainBonusBall(Lotto lotto) {
		return lotto.isContain(this.bonusBall);
	}
}
