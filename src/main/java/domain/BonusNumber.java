package domain;

public class BonusNumber {
	private final int bonusNumber;

	public BonusNumber(int userInput) {
		validateBonusNumber(userInput);
		this.bonusNumber = userInput;
	}

	private void validateBonusNumber(int bonusNumber) {
		if (bonusNumber < 1 || bonusNumber > 45) {
			throw new IllegalArgumentException("[ERROR] 1이상 45 이하의 숫자만 허용됩니다.");
		}
	}

	public int getNumber() {
		return this.bonusNumber;
	}
}
