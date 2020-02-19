package lotto.domain;

public class BonusNumber {
	private int bonusNumber;

	public BonusNumber(int bonusNumber) {
		this.bonusNumber = bonusNumber;
	}

	private boolean isInvalidNumberRange(int number) {
		return number > 45 || number < 1;
	}

	public int getBonusNumber() {
		return bonusNumber;
	}
}
