package domain;

public class LottoNumber {
	private final int number;

	public LottoNumber(int userInput) {
		validateLottoNumber(userInput);
		this.number = userInput;
	}

	private void validateLottoNumber(int bonusNumber) {
		if (bonusNumber < 1 || bonusNumber > 45) {
			throw new IllegalArgumentException("[ERROR] 1이상 45 이하의 숫자만 허용됩니다.");
		}
	}

	public int getLottoNumber() {
		return this.number;
	}
}
