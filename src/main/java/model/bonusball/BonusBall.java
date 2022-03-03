package model.bonusball;

import model.lotto.LottoNumber;

public class BonusBall {
	private static final String BONUS_BALL_RANGE_ERROR_MESSAGE = "[Error]: 보너스 볼은 %d~%d의 숫자만 입력해주세요.";

	private final int number;

	public BonusBall(int number) {
		LottoNumber.validateOutOfRange(number, BONUS_BALL_RANGE_ERROR_MESSAGE);
		this.number = number;
	}

	public BonusBallDTO getBonusBallDTO() {
		return new BonusBallDTO(number);
	}

}
