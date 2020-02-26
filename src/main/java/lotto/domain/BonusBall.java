package lotto.domain;

import lotto.validator.InputValidator;

public class BonusBall {
	private final LottoNo bonusBall;

	public BonusBall(String bonusBall) {
        InputValidator.validateNumber(bonusBall);
        this.bonusBall = new LottoNo(Integer.parseInt(bonusBall));
    }

	public boolean contains(Lotto lotto) {
		if (lotto == null) {
			return false;
		}
		return lotto.contains(this.bonusBall);
	}
}
