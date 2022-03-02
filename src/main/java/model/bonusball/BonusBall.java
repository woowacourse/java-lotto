package model.bonusball;

import model.lotto.LottoNumber;
import utils.InputValidateUtils;

public class BonusBall {
	private static final String BONUS_BALL_BLANK_ERROR_MESSAGE = "[Error]: 보너스 볼을 입력해주세요.";
	private static final String BONUS_BALL_NUMBER_ERROR_MESSAGE = "[Error]: 보너스 볼은 숫자여야 합니다.";
	private static final String BONUS_BALL_RANGE_ERROR_MESSAGE = "[Error]: 보너스 볼은 1~45의 숫자만 입력해주세요.";

	private final int number;

	public BonusBall(String number) {
		InputValidateUtils.inputBlank(number, BONUS_BALL_BLANK_ERROR_MESSAGE);
		InputValidateUtils.inputNumber(number, BONUS_BALL_NUMBER_ERROR_MESSAGE);
		LottoNumber.validateOutOfRange(Integer.parseInt(number), BONUS_BALL_RANGE_ERROR_MESSAGE);
		this.number = makeBonusBallToNumber(number);
	}

	private int makeBonusBallToNumber(String number) {
		return Integer.parseInt(number);
	}

	public BonusBallDTO getBonusBallDTO() {
		return new BonusBallDTO(number);
	}

	public int getNumber() {
		return Integer.valueOf(number);
	}

}
