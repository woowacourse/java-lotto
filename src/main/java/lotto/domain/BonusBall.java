package lotto.domain;

import lotto.validator.Validator;

public class BonusBall {
	private int bonusBall;

	public BonusBall(String bonusBall) {
		Validator.validateInteger(bonusBall);
		int bonusNo = Integer.valueOf(bonusBall);
		Validator.validateLottoRange(bonusNo);
		this.bonusBall = bonusNo;
	}

	public boolean isContainBonusBall(Lotto lotto) {
		return lotto.isContain(this.bonusBall);
	}
}
