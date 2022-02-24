package domain;

import static constant.ErrorConstant.START_ERROR;
import static constant.LottoConstant.MAX_NUMBER;
import static constant.LottoConstant.MIN_NUMBER;

public class BonusNumber {

	private static final String NUMBER_IN_RANGE = START_ERROR + "1이상 45 이하의 숫자만 허용됩니다.";

	private final int bonusNumber;

	public BonusNumber(int userInput) {
		validateBonusNumber(userInput);
		this.bonusNumber = userInput;
	}

	private void validateBonusNumber(int bonusNumber) {
		if (bonusNumber < MIN_NUMBER || bonusNumber > MAX_NUMBER) {
			throw new IllegalArgumentException(NUMBER_IN_RANGE);
		}
	}

	public int getNumber() {
		return this.bonusNumber;
	}
}
