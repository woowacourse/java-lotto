package lotto.domain;

public class BonusNumber {
	private int bonusNumber;

	public BonusNumber(int bonusNumber) {
		if(isInvalidNumberRange(bonusNumber)) {
			throw new IllegalArgumentException("보너스 넘버의 범위:1-45");
		}
		this.bonusNumber = bonusNumber;
	}

	private boolean isInvalidNumberRange(int number) {
		return number > 45 || number < 1;
	}

	public int getBonusNumber() {
		return bonusNumber;
	}
}
