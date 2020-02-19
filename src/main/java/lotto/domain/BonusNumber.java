package lotto.domain;

public class BonusNumber {
	private static final int MAX_LOTTO_NUMBER_RANGE = 45;
	private static final int MIN_LOTTO_NUMBER_RANGE = 1;

	private int bonusNumber;

	public BonusNumber(int bonusNumber) {
		if (isInvalidNumberRange(bonusNumber)) {
			throw new IllegalArgumentException("보너스 넘버의 범위:1-45");
		}
		this.bonusNumber = bonusNumber;
	}

	private boolean isInvalidNumberRange(int number) {
		return number > MAX_LOTTO_NUMBER_RANGE || number < MIN_LOTTO_NUMBER_RANGE;
	}

	public int getBonusNumber() {
		return bonusNumber;
	}
}
